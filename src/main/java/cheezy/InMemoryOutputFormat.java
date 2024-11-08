package cheezy;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.DoubleWritable;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class InMemoryOutputFormat extends OutputFormat<Text, Text> {

    private static List<Cheese> results = new ArrayList<>();

    public static void setResults(List<Cheese> resultsList) {
        results = resultsList;
    }

    public static List<Cheese> getResults() {
        return results;
    }

    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        return new RecordWriter<Text, Text>() {
            @Override
            public void write(Text key, Text value) throws IOException, InterruptedException {
                // Here, we're assuming value contains all the necessary data to construct a Cheese object
                String[] cheeseData = value.toString().split(",");

                // Check if the data has 13 fields
                if (cheeseData.length == 13) {
                    Cheese cheese = new Cheese(
                            cheeseData[0], // cheeseId
                            cheeseData[1], // manufacturerProvCode
                            cheeseData[2], // manufacturingTypeEn
                            Double.parseDouble(cheeseData[3]), // moisturePercent
                            cheeseData[4], // flavourEn
                            cheeseData[5], // characteristicsEn
                            Boolean.parseBoolean(cheeseData[6]), // isOrganic
                            cheeseData[7], // categoryTypeEn
                            cheeseData[8], // milkTypeEn
                            cheeseData[9], // milkTreatmentTypeEn
                            cheeseData[10], // rindTypeEn
                            cheeseData[11], // cheeseName
                            cheeseData[12] // fatLevel
                    );
                    results.add(cheese);
                } else {
                    // Handle incorrect number of fields if necessary (logging or throwing exception)
                    System.err.println("Invalid data: " + value);
                }
            }

            @Override
            public void close(TaskAttemptContext context) throws IOException, InterruptedException {
                // Cleanup if needed
            }
        };
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {
        // No output checking needed as we're collecting in memory
    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return null; // No actual commit since we're not writing to a file
    }
}
