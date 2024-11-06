package cheezy.Calculators;

import java.util.List;
import java.util.stream.Collectors;
import cheezy.Cheese;
import cheezy.CheeseData;

public class OrganicCheesePercentage {
    public static String calculate(String provCode, String categoryType) {
        List<Cheese> filteredCheeses = CheeseData.getAllCheeses().stream()
                .filter(c -> c.getManufacturerProvCode().equals(provCode) &&
                        c.getCategoryTypeEn().equals(categoryType))
                .collect(Collectors.toList());

        long organicCount = filteredCheeses.stream().filter(Cheese::isOrganic).count();
        double percentage = 100.0 * organicCount / filteredCheeses.size();

        return "Organic Cheese Percentage: " + percentage + "%";
    }
}