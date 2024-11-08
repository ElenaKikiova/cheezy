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
            // Skip the header row
            reader.readLine();

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

    private static double parseDouble(String value) {
        if (value == null || value.isEmpty()) {
            return 0.0; // Return the default value if the field is empty
        }
        try {
            return Double.parseDouble(value); // Try to parse the value as a double
        } catch (NumberFormatException e) {
            return 0.0; // Return default value if parsing fails
        }
    }

    private static Cheese parseCheese(String line) {
        // Split CSV line by comma
        String[] fields = line.split(",");
        if (fields.length < 13) {
            return null; // Not enough data to create a Cheese object
        }

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isEmpty()) {
                continue;
            }
            fields[i] = fields[i].replace("\"", "").trim();
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
    }

    public static List<Cheese> getAllCheeses() {
        return allCheeses;
    }
}
