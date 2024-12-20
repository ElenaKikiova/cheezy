package cheezy.hadoop;

import cheezy.Cheese;
import cheezy.CheeseData;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;
import java.util.Objects;

public class CheeseMapper extends Mapper<Object, Text, Text, Text> {

    private String filterProvince;
    private String filterCategory;
    private String filterMilkType;
    private MultipleOutputs<Text, Text> multipleOutputs;

    @Override
    protected void setup(Context context) {
        // Get the filters
        filterProvince = context.getConfiguration().get("filter.province");
        filterCategory = context.getConfiguration().get("filter.category");
        filterMilkType = context.getConfiguration().get("filter.milkType");

        // Initialize MultipleOutputs
        multipleOutputs = new MultipleOutputs<>(context);
    }

    protected boolean equals(String value1, String value2){
        if(Objects.equals(value2, "All")) return true;
        if(value1 == null || value2 == null || value1.isEmpty() || value2.isEmpty()) return false;
        else return value1.equals(value2);
    }

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        // Parse the cheese data
        Cheese cheese = CheeseData.parseCheese(line);

        // Apply filters
        boolean matchesProvince = equals(cheese.getManufacturerProvCode(), filterProvince);
        boolean matchesCategory = equals(cheese.getCategoryTypeEn(), filterCategory);
        boolean matchesMilkType = equals(cheese.getMilkTypeEn(), filterMilkType);

        if (matchesProvince && matchesCategory && matchesMilkType) {
            context.write(new Text("calculation"), new Text(cheese.getMoisturePercent() + "," + cheese.isOrganic()));

            // Write to "filter" output
            multipleOutputs.write(
                    "filteredCheese",
                    new Text("filter"),
                    new Text(cheese.cheeseToString())
            );
        }
    }
}
