package cheezy;

public class Cheese {
    private String cheeseId;
    private String manufacturerProvCode;
    private String manufacturingTypeEn;
    private double moisturePercent;
    private String flavourEn;
    private String characteristicsEn;
    private boolean isOrganic;
    private String categoryTypeEn;
    private String milkTypeEn;
    private String milkTreatmentTypeEn;
    private String rindTypeEn;
    private String cheeseName;
    private String fatLevel;

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