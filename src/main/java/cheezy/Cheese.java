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

    // Setters
    public void setCheeseId(String cheeseId) { this.cheeseId = cheeseId; }
    public void setManufacturerProvCode(String manufacturerProvCode) { this.manufacturerProvCode = manufacturerProvCode; }
    public void setManufacturingTypeEn(String manufacturingTypeEn) { this.manufacturingTypeEn = manufacturingTypeEn; }
    public void setMoisturePercent(double moisturePercent) { this.moisturePercent = moisturePercent; }
    public void setFlavourEn(String flavourEn) { this.flavourEn = flavourEn; }
    public void setCharacteristicsEn(String characteristicsEn) { this.characteristicsEn = characteristicsEn; }
    public void setOrganic(boolean organic) { isOrganic = organic; }
    public void setCategoryTypeEn(String categoryTypeEn) { this.categoryTypeEn = categoryTypeEn; }
    public void setMilkTypeEn(String milkTypeEn) { this.milkTypeEn = milkTypeEn; }
    public void setMilkTreatmentTypeEn(String milkTreatmentTypeEn) { this.milkTreatmentTypeEn = milkTreatmentTypeEn; }
    public void setRindTypeEn(String rindTypeEn) { this.rindTypeEn = rindTypeEn; }
    public void setCheeseName(String cheeseName) { this.cheeseName = cheeseName; }
    public void setFatLevel(String fatLevel) { this.fatLevel = fatLevel; }

    // toString method
    @Override
    public String toString() {
        return "Cheese{" +
                "cheeseId='" + cheeseId + '\'' +
                ", manufacturerProvCode='" + manufacturerProvCode + '\'' +
                ", manufacturingTypeEn='" + manufacturingTypeEn + '\'' +
                ", moisturePercent=" + moisturePercent +
                ", flavourEn='" + flavourEn + '\'' +
                ", characteristicsEn='" + characteristicsEn + '\'' +
                ", isOrganic=" + isOrganic +
                ", categoryTypeEn='" + categoryTypeEn + '\'' +
                ", milkTypeEn='" + milkTypeEn + '\'' +
                ", milkTreatmentTypeEn='" + milkTreatmentTypeEn + '\'' +
                ", rindTypeEn='" + rindTypeEn + '\'' +
                ", cheeseName='" + cheeseName + '\'' +
                ", fatLevel='" + fatLevel + '\'' +
                '}';
    }
}