package cheezy;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CheeseMapper extends Mapper<Object, Text, Text, DoubleWritable> {

    private static final DoubleWritable moisture = new DoubleWritable();
    private static final DoubleWritable organicPercentage = new DoubleWritable();
    private Text keyOutput = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Skip the header line
        String line = value.toString();
        if (line.startsWith("CheeseId")) {
            return;
        }

        // Parse CSV
        String[] fields = line.split(",");
        if (fields.length < 13) return;

        String province = fields[1].trim();
        String category = fields[7].trim();
        String milkType = fields[8].trim();
        boolean isOrganic = fields[6].equals("1");
        double moisturePercent = Double.parseDouble(fields[3].trim());

        // Get filter values from the context (you can pass them in the job config)
        String selectedProvince = context.getConfiguration().get("selected.province", "All");
        String selectedCategory = context.getConfiguration().get("selected.category", "All");
        String selectedMilkType = context.getConfiguration().get("selected.milkType", "All");

        // Filter logic based on the selected filters
        if ((selectedProvince.equals("All") || province.equals(selectedProvince)) &&
                (selectedCategory.equals("All") || category.equals(selectedCategory)) &&
                (selectedMilkType.equals("All") || milkType.equals(selectedMilkType))) {

            // Emit the average moisture or organic calculation
            keyOutput.set(selectedCategory + "_" + selectedProvince + "_" + selectedMilkType);
            moisture.set(moisturePercent);
            context.write(keyOutput, moisture);

            // For organic calculation (simple count per category)
            if (isOrganic) {
                organicPercentage.set(1.0);
                context.write(keyOutput, organicPercentage);
            }
        }
    }
}
