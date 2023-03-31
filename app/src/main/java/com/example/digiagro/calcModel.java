package com.example.digiagro;

public class calcModel {
    String mop, ssp, urea,area;

    public calcModel() {
    }

    public calcModel(String mop, String ssp, String urea, String area) {
        this.mop = mop;
        this.ssp = ssp;
        this.urea = urea;
        this.area = area;
    }

    public String getMop() {
        return mop;
    }

    public void setMop(String mop) {
        this.mop = mop;
    }

    public String getSsp() {
        return ssp;
    }

    public void setSsp(String ssp) {
        this.ssp = ssp;
    }

    public String getUrea() {
        return urea;
    }

    public void setUrea(String urea) {
        this.urea = urea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
