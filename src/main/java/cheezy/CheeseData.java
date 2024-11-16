package cheezy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheeseData {
    private static final List<Cheese> allCheeses = new ArrayList<>();

    public static void loadCheeseData(String hdfsPath) {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", Constants.HDFS_ADDRESS);

        try (FileSystem fs = FileSystem.get(configuration);
             FSDataInputStream inputStream = fs.open(new Path(hdfsPath));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            // Skip header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                Cheese cheese = parseCheese(line);
                if (cheese != null) {
                    allCheeses.add(cheese);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading data file from HDFS: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "There was an error while reading the cheese data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static double parseDouble(String value) {
        // If value si empty or null or not parsable to double, return 0.0
        if (value == null || value.isEmpty()) {
            return 0.0;
        }
        try {
            value = value.replace("\"", "").trim();
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static Cheese parseCheese(String line) {

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withQuoteChar('"')
                .build();

        try {
            String[] fields = parser.parseLine(line);

            if(fields.length < 13){
                return null;
            }
            // Clean and handle missing values
            for (int i = 0; i < fields.length; i++) {
                if (fields[i] == null || fields[i].isEmpty()) {
                    fields[i] = null;
                } else {
                    fields[i] = fields[i].replace("\"", "").trim();
                }
            }

            // Parse each field from the CSV
            String cheeseId = fields[0];
            String manufacturerProvCode = fields[1];
            String manufacturingTypeEn = fields[2];
            double moisturePercent = parseDouble(fields[3]);
            String flavourEn = fields[4];
            String characteristicsEn = fields[5];
            boolean isOrganic = fields[6].equals("1");
            String categoryTypeEn = fields[7];
            String milkTypeEn = fields[8];
            String milkTreatmentTypeEn = fields[9];
            String rindTypeEn = fields[10];
            String cheeseName = fields[11];
            String fatLevel = fields[12];


            // Return the Cheese
            return new Cheese(
                    cheeseId, manufacturerProvCode, manufacturingTypeEn,
                    moisturePercent, flavourEn, characteristicsEn,
                    isOrganic, categoryTypeEn, milkTypeEn,
                    milkTreatmentTypeEn, rindTypeEn, cheeseName, fatLevel
            );

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Cheese> getAllCheeses() {
        return allCheeses;
    }
    public static String readCalculationResultFromHDFS(String outputPath) throws IOException {
        List<Cheese> filteredCheeses = new ArrayList<>();
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        Path path = new Path(outputPath + "/part-r-00000");

        try (FileSystem fs = FileSystem.get(conf);
             FSDataInputStream inputStream = fs.open(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            String calculation = "";
            String result = "";
            while ((line = reader.readLine()) != null) {
                // Split the line into key and value (tab-separated)
                String[] keyValue = line.split("\t");
                calculation = keyValue[0]; // The key
                result = keyValue[1]; // The value
            }
            return calculation + ": " + (result.isEmpty() ? "0%" : result);

        }
        catch (IOException e){
            System.err.println(e);
        }
        return "";
    }


}
