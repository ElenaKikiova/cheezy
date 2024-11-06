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
            // Skip the header row in the CSV (if it's there)
            reader.readLine(); // Assuming first line is header

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
        // Split CSV line by comma (adjust if your file uses tabs or another delimiter)
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

        // Parse each field from the CSV (ensure correct mapping with headers)
        String cheeseId = fields[0];  // CheeseId
        String manufacturerProvCode = fields[1];  // ManufacturerProvCode
        String manufacturingTypeEn = fields[2];  // ManufacturingTypeEn
        double moisturePercent = parseDouble(fields[3]);  // MoisturePercent
        String flavourEn = fields[4];  // FlavourEn
        String characteristicsEn = fields[5];  // CharacteristicsEn
        boolean isOrganic = fields[6].equals("1");  // Organic (assuming '1' means organic)
        String categoryTypeEn = fields[7];  // CategoryTypeEn
        String milkTypeEn = fields[8];  // MilkTypeEn
        String milkTreatmentTypeEn = fields[9];  // MilkTreatmentTypeEn
        String rindTypeEn = fields[10];  // RindTypeEn
        String cheeseName = fields[11];  // CheeseName
        String fatLevel = fields[12];  // FatLevel

        System.out.println(cheeseId);

        // Return the Cheese object
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
