package cheezy;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CheeseReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    private final DoubleWritable result = new DoubleWritable();
    private double totalMoisture = 0;
    private long totalOrganic = 0;
    private long totalCount = 0;

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        totalMoisture = 0;
        totalOrganic = 0;
        totalCount = 0;

        // Process each value for the current key
        for (DoubleWritable value : values) {
            totalCount++;
            double val = value.get();

            // Calculate average moisture
            if (val >= 0) { // Moisture value
                totalMoisture += val;
            } else { // Organic count
                totalOrganic++;
            }
        }

        // Calculate the average moisture or organic percentage
        if (totalCount > 0) {
            double averageMoisture = totalMoisture / totalCount;
            result.set(averageMoisture);
            context.write(key, result);
        }

        if (totalCount > 0) {
            double organicPercentage = (double) totalOrganic / totalCount * 100;
            result.set(organicPercentage);
            context.write(key, result);
        }
    }
}
