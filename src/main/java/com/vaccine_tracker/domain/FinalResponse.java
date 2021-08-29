package com.vaccine_tracker.domain;

import java.util.List;

public class FinalResponse {

    private String isAvailable;
    private String date;
    private List<VaccineInfo> vaccineInfos;


    public List<VaccineInfo> getVaccineInfos() {
        return vaccineInfos;
    }

    public void setVaccineInfos(List<VaccineInfo> vaccineInfos) {
        this.vaccineInfos = vaccineInfos;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Details{" +
                "Availability='" + isAvailable + '\'' +
                ", date='" + date + '\'' +
                ", vaccineInfos=" + vaccineInfos +
                '}';
    }
}
