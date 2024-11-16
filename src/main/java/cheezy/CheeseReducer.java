package cheezy;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cheezy.CheeseData.parseDouble;

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
            boolean isOrganic = data[1].equals("1");

            totalMoisture += moisture;
            if (isOrganic) organicCount++;
            totalCount++;
        }


        // Depending on the calculation type, output different results
        String calculationType = context.getConfiguration().get("calculation");
        if (calculationType.equals("Average Moisture Percent")) {
            double avgMoisture = totalMoisture / totalCount;
            System.out.println(key + " => total mositure" + totalMoisture + "; total count" + totalCount + "; avg " + avgMoisture);
            context.write(key, new Text("Average Moisture Percent: " + avgMoisture));
        } else if (calculationType.equals("organicPercentage")) {
            double organicPercentage = (organicCount / (double) totalCount) * 100;
            context.write(key, new Text("Organic Percentage: " + organicPercentage));
        }
    }
}
