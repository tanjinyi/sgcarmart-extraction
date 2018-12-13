package ai.preferred.crawler.sgcarmart.model;

public class CarDetails {
    private String name;
    private int price; //need to remove $ and /yr
    private int depreciation;
    private String regDate;
    private String manufactured;
    private String mileage; //need to remove (14k/yr)
    private String transmission;
    private int engineCap; //need to remove ' cc'
    private int roadTax; //need to remove $ and /yr
    private String power;
    private int curbWeight; //need to remove 'kg'
    private String features;
    private String accessories;
    private String description;
    private int coe; //need to remove $
    private int omv; //need to remove $
    private int arf; //need to remove $
    private int deregValue; //need to remove $
    private int noOfOwners;
    private String typeOfVeh;
    private String category;
    private boolean availability;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(int depreciation) {
        this.depreciation = depreciation;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getManufactured() {
        return manufactured;
    }

    public void setManufactured(String manufactured) {
        this.manufactured = manufactured;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getEngineCap() {
        return engineCap;
    }

    public void setEngineCap(int engineCap) {
        this.engineCap = engineCap;
    }

    public int getRoadTax() {
        return roadTax;
    }

    public void setRoadTax(int roadTax) {
        this.roadTax = roadTax;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getCurbWeight() {
        return curbWeight;
    }

    public void setCurbWeight(int curbWeight) {
        this.curbWeight = curbWeight;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoe() {
        return coe;
    }

    public void setCoe(int coe) {
        this.coe = coe;
    }

    public int getOmv() {
        return omv;
    }

    public void setOmv(int omv) {
        this.omv = omv;
    }

    public int getArf() {
        return arf;
    }

    public void setArf(int arf) {
        this.arf = arf;
    }

    public int getDeregValue() {
        return deregValue;
    }

    public void setDeregValue(int deregValue) {
        this.deregValue = deregValue;
    }

    public int getNoOfOwners() {
        return noOfOwners;
    }

    public void setNoOfOwners(int noOfOwners) {
        this.noOfOwners = noOfOwners;
    }

    public String getTypeOfVeh() {
        return typeOfVeh;
    }

    public void setTypeOfVeh(String typeOfVeh) {
        this.typeOfVeh = typeOfVeh;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
