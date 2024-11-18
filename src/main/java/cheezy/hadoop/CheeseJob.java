package cheezy.hadoop;

import cheezy.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class CheeseJob {

    public static CheeseResult runCheeseJob(String inputPath, String province, String category, String milkType, String calculation) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", Constants.HDFS_ADDRESS);

        // Set filters
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

        String outputBasePath = "/user/elena.kikiova/output/";
        Path mainOutputPath = new Path(outputBasePath);
        FileOutputFormat.setOutputPath(job, mainOutputPath);
        mainOutputPath.getFileSystem(conf).delete(mainOutputPath, true);

        MultipleOutputs.addNamedOutput(job, "calculation", TextOutputFormat.class, Text.class, Text.class);
        MultipleOutputs.addNamedOutput(job, "filteredCheese", TextOutputFormat.class, Text.class, Text.class);

        if (job.waitForCompletion(true)) {
            return new CheeseResult(
                    CheeseData.readCalculationResultsFromHDFS(outputBasePath),
                    CheeseData.readFilteredCheeseFromHDFS(outputBasePath)
            );
        } else {
            throw new Exception("Hadoop job failed.");
        }
    }

}
