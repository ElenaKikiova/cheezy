package cheezy.hadoop;

import cheezy.CheeseData;
import cheezy.Constants;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;
public class CheeseReducer extends Reducer<Text, Text, Text, Text> {

    private MultipleOutputs<Text, Text> multipleOutputs;

    @Override
    protected void setup(Context context) {
        multipleOutputs = new MultipleOutputs<>(context);
    }

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        if (key.toString().equals("calculation")) {
            double totalMoisture = 0;
            int organicCount = 0;
            int totalCount = 0;

            // Aggregate data: calculate total moisture and organic count
            for (Text value : values) {
                String[] data = value.toString().split(",");
                double moisture = CheeseData.parseDouble(data[0]);
                boolean isOrganic = data[1].equals("true");

                totalMoisture += moisture;
                if (isOrganic) organicCount++;
                totalCount++;
            }

            // Calculate and output the final aggregated result (Moisture or Organic Percentage)
            String calculationType = context.getConfiguration().get("calculation");
            if (calculationType.equals(Constants.CALCULATION_TYPES[0])) {
                double avgMoisture = totalMoisture / totalCount;
                multipleOutputs.write(
                        "calculation",
                        new Text(Constants.CALCULATION_TYPES[0]),
                        new Text(String.format("%.1f", avgMoisture) + "%")
                );
            } else if (calculationType.equals(Constants.CALCULATION_TYPES[1])) {
                double organicPercentage = (organicCount / (double) totalCount) * 100;
                multipleOutputs.write(
                        "calculation",
                        new Text(Constants.CALCULATION_TYPES[1]),
                        new Text(String.format("%.1f", organicPercentage) + "%")
                );
            }
        } else if (key.toString().equals("filter")) {
            for (Text value : values) {
                multipleOutputs.write(
                        "filteredCheese",
                        key,
                        value
                );
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        multipleOutputs.close();
    }
}
