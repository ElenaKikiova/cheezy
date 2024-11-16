package cheezy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

public class CheeseJob {
    private static final Logger logger = Logger.getLogger(CheeseJob.class);

    public static void main(String[] args) {
        logger.setLevel(Level.ERROR);
    }

    public static List<Cheese> runCheeseJob(String inputPath, String province, String category, String milkType, String calculation) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");

        // Set filtering parameters
        conf.set("filter.province", province);
        conf.set("filter.category", category);
        conf.set("filter.milkType", milkType);
        conf.set("calculation", calculation);

        Job job = Job.getInstance(conf, "Cheese Filter Job");
        job.setJarByClass(CheeseJob.class);

        job.setMapperClass(CheeseMapper.class);
        job.setReducerClass(CheeseReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(inputPath));

        // Set output path in HDFS and check for existing files
        String outputPathStr = "/user/elena.kikiova/output/cheese-filtered";
        Path outputPath = new Path(outputPathStr);
        FileOutputFormat.setOutputPath(job, outputPath);
        outputPath.getFileSystem(conf).delete(outputPath, true);  // Clear any existing output

        // Run the job and wait for completion
        if (job.waitForCompletion(true)) {
            // If job is successful, retrieve results and load them into memory
            return CheeseData.readFilteredCheesesFromHDFS(outputPathStr);  // Implement this to read filtered results
        } else {
            throw new Exception("Hadoop job failed.");
        }
    }
}
