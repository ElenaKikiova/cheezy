package cheezy;

public class Cheese {
    private final String cheeseId;
    private final String manufacturerProvCode;
    private final String manufacturingTypeEn;
    private final double moisturePercent;
    private final String flavourEn;
    private final String characteristicsEn;
    private final boolean isOrganic;
    private final String categoryTypeEn;
    private final String milkTypeEn;
    private final String milkTreatmentTypeEn;
    private final String rindTypeEn;
    private final String cheeseName;
    private final String fatLevel;

    // Constructor
    public Cheese(String cheeseId, String manufacturerProvCode, String manufacturingTypeEn,
                  double moisturePercent, String flavourEn, String characteristicsEn,
                  boolean isOrganic, String categoryTypeEn, String milkTypeEn,
                  String milkTreatmentTypeEn, String rindTypeEn, String cheeseName,
                  String fatLevel) {
        this.cheeseId = cheeseId;
        this.manufacturerProvCode = manufacturerProvCode;
        this.manufacturingTypeEn = manufacturingTypeEn;
        this.moisturePercent = moisturePercent;
        this.flavourEn = flavourEn;
        this.characteristicsEn = characteristicsEn;
        this.isOrganic = isOrganic;
        this.categoryTypeEn = categoryTypeEn;
        this.milkTypeEn = milkTypeEn;
        this.milkTreatmentTypeEn = milkTreatmentTypeEn;
        this.rindTypeEn = rindTypeEn;
        this.cheeseName = cheeseName;
        this.fatLevel = fatLevel;
    }

    public String cheeseToString(){
        return cheeseId + "," +
                manufacturerProvCode + "," +
                manufacturingTypeEn + "," +
                moisturePercent + "," +
                flavourEn + "," +
                characteristicsEn + "," +
                (isOrganic ? "Yes" : "No") + "," +
                categoryTypeEn + "," +
                milkTypeEn + "," +
                milkTreatmentTypeEn + "," +
                rindTypeEn + "," +
                cheeseName + "," +
                fatLevel;
    }

    // Getters
    public String getCheeseId() { return cheeseId; }
    public String getManufacturerProvCode() { return manufacturerProvCode; }
    public String getManufacturingTypeEn() { return manufacturingTypeEn; }
    public double getMoisturePercent() { return moisturePercent; }
    public String getFlavourEn() { return flavourEn; }
    public String getCharacteristicsEn() { return characteristicsEn; }
    public boolean isOrganic() { return isOrganic; }
    public String getCategoryTypeEn() { return categoryTypeEn; }
    public String getMilkTypeEn() { return milkTypeEn; }
    public String getMilkTreatmentTypeEn() { return milkTreatmentTypeEn; }
    public String getRindTypeEn() { return rindTypeEn; }
    public String getCheeseName() { return cheeseName; }
    public String getFatLevel() { return fatLevel; }

}