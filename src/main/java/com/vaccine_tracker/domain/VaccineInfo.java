package com.vaccine_tracker.domain;

public class VaccineInfo {

    private String vaccineName;
    private int totalAvailable;
    private int dose1;
    private int dose2;
    private String vaccineCenterName;
    private String vaccineCenterAddress;
    private String freeType;


    public String getFreeType() {
        return freeType;
    }

    public void setFreeType(String freeType) {
        this.freeType = freeType;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public int getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(int totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    public int getDose1() {
        return dose1;
    }

    public void setDose1(int dose1) {
        this.dose1 = dose1;
    }

    public int getDose2() {
        return dose2;
    }

    public void setDose2(int dose2) {
        this.dose2 = dose2;
    }

    public String getVaccineCenterName() {
        return vaccineCenterName;
    }

    public void setVaccineCenterName(String vaccineCenterName) {
        this.vaccineCenterName = vaccineCenterName;
    }

    public String getVaccineCenterAddress() {
        return vaccineCenterAddress;
    }

    public void setVaccineCenterAddress(String vaccineCenterAddress) {
        this.vaccineCenterAddress = vaccineCenterAddress;
    }

    @Override
    public String toString() {
        return "VaccineInfo{" +
                "vaccineName='" + vaccineName + '\'' +
                ", totalAvailable=" + totalAvailable +
                ", dose1=" + dose1 +
                ", dose2=" + dose2 +
                ", vaccineCenterName='" + vaccineCenterName + '\'' +
                ", vaccineCenterAddress='" + vaccineCenterAddress + '\'' +
                ", freeType='" + freeType + '\'' +
                '}';
    }

}
