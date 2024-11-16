package cheezy;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CheeseReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double totalMoisture = 0;
        int organicCount = 0;
        int totalCount = 0;

        // Iterate through all the values and aggregate
        for (Text value : values) {
            String[] data = value.toString().split(",");
            double moisture = CheeseData.parseDouble(data[0]);
            boolean isOrganic = data[1].equals("true");

            totalMoisture += moisture;
            if (isOrganic) organicCount++;
            totalCount++;
        }


        // Depending on the calculation type, output different results
        String calculationType = context.getConfiguration().get("calculation");
        System.out.println(calculationType);
        if (calculationType.equals(Constants.CALCULATION_TYPES[0])) {
            System.out.println(totalMoisture + " divided by " + totalCount);
            double avgMoisture = totalMoisture / totalCount;
            context.write(new Text(Constants.CALCULATION_TYPES[0]), new Text(String.format("%.1f", avgMoisture) + "%"));
        } else if (calculationType.equals(Constants.CALCULATION_TYPES[1])) {
            System.out.println(organicCount + " divided by " + totalCount);
            double organicPercentage = (organicCount / (double) totalCount) * 100;
            context.write(new Text(Constants.CALCULATION_TYPES[1]), new Text(String.format("%.1f", organicPercentage) + "%"));
        }
    }
}
