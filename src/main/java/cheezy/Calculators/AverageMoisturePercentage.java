package cheezy.Calculators;

import java.util.List;
import java.util.stream.Collectors;
import cheezy.Cheese;
import cheezy.CheeseData;

public class AverageMoisturePercentage {
    public static String calculate(String categoryType, String milkType) {
        // Filter cheeses by category and milk type
        List<Cheese> filteredCheeses = CheeseData.getAllCheeses().stream()
                .filter(c -> c.getCategoryTypeEn().equals(categoryType) &&
                        c.getMilkTypeEn().equals(milkType))
                .collect(Collectors.toList());

        // Calculate average moisture
        double averageMoisture = filteredCheeses.stream()
                .mapToDouble(Cheese::getMoisturePercent)
                .average()
                .orElse(0);

        return "Average Moisture Percentage: " + averageMoisture;
    }
}