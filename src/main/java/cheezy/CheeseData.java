package cheezy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheeseData {
    private static List<Cheese> allCheeses = new ArrayList<>();

    public static void loadCheeseData(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Cheese cheese = parseCheese(line);
                if (cheese != null) {
                    allCheeses.add(cheese);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
        }
    }

    private static Cheese parseCheese(String line) {
        // Split CSV line by comma
        String[] fields = line.split(",");
        if (fields.length < 13) {
            return null; // Not enough data to create a Cheese object
        }

        // Parse each field from the CSV (example format)
        String cheeseId = fields[0];
        String manufacturerProvCode = fields[1];
        String manufacturingTypeEn = fields[2];
        double moisturePercent = Double.parseDouble(fields[3]);
        String flavourEn = fields[4];
        String characteristicsEn = fields[5];
        boolean isOrganic = fields[6].equals("1");
        String categoryTypeEn = fields[7];
        String milkTypeEn = fields[8];
        String milkTreatmentTypeEn = fields[9];
        String rindTypeEn = fields[10];
        String cheeseName = fields[11];
        String fatLevel = fields[12];

        return new Cheese(
                cheeseId, manufacturerProvCode, manufacturingTypeEn,
                moisturePercent, flavourEn, characteristicsEn,
                isOrganic, categoryTypeEn, milkTypeEn,
                milkTreatmentTypeEn, rindTypeEn, cheeseName, fatLevel
        );
    }

    public static List<Cheese> getAllCheeses() {
        return allCheeses;
    }
}