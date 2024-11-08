package cheezy;

public class Constants {

    public static final String[] TABLE_COLUMNS = {
            "Cheese ID",
            "Name",
            "Province",
            "Category",
            "Milk Type",
            "Moisture %",
            "Organic",
            "Flavour",
            "Fat %"
    };
    public static final String[] MANUFACTURER_PROV_CODES = {"All", "AB", "BC", "NS", "ON", "QC"};
    public static final String[] CATEGORY_TYPES = {"All", "Fresh Cheese",
            "Semi-soft Cheese",
            "Veined Cheeses",
            "Hard Cheese",
            "Soft Cheese",
            "Firm Cheese"};
    public static final String[] MILK_TYPES = {"All", "Cow", "Ewe", "Goat", "Cow and Goat"};

    public static final String[] CALCULATION_TYPES = {"Average Moisture Percent", "Organic Cheese Percentage"};

}
