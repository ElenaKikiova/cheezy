package cheezy;

import cheezy.App.CheeseApp;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CheeseJob {
    private static final Logger logger = Logger.getLogger(CheeseApp.class.getName()); // Initialize the logger

    public static List<Cheese> runCheeseJob(String inputPath, String province, String category, String milkType) throws Exception {
        // Log the start of the job
        logger.info("Starting the Cheese job with filters: " + province + ", " + category + ", " + milkType);

        // Configure the Hadoop job
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        conf.setInt("mapreduce.job.timeout", 600000);
        conf.set("selected.province", province);
        conf.set("selected.category", category);
        conf.set("selected.milkType", milkType);

        Job job = Job.getInstance(conf, "Cheese Analysis");
        job.setJarByClass(CheeseJob.class);

        // Set Mapper and Reducer classes
        job.setMapperClass(CheeseMapper.class);
        job.setReducerClass(CheeseReducer.class);

        // Set the output key and value types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        // Set input path
        FileInputFormat.addInputPath(job, new Path(inputPath));

        // Use the custom OutputFormat to collect results in memory
        List<Cheese> cheeseResults = new ArrayList<>();
        job.setOutputFormatClass(InMemoryOutputFormat.class);
        InMemoryOutputFormat.setResults(cheeseResults);

        // Log before job submission
        logger.info("Submitting the job...");

        // Add additional logging after submission to check job status
        boolean success = false;
        try {
            success = job.waitForCompletion(true);
        } catch (Exception e) {
            logger.severe("Job failed during execution: " + e.getMessage());
            e.printStackTrace();
        }

        if (success) {
            logger.info("Job completed successfully.");
        } else {
            logger.severe("Job failed.");
        }

        // Return the results after job completion
        return success ? cheeseResults : new ArrayList<>();
    }
}
