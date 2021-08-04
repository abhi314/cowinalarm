package com.abhi.cowinalarm.model;

public class Session {
    private int center_id;
    private String name;
    private int pincode;
    private String fee_type;
    private String date;
    private int available_capacity;
    private int available_capacity_dose1;
    private int available_capacity_dose2;
    private String fee;
    private int min_age_limit;
    private String vaccine;

//    public String from;
//    public String to;
//    public String address;
//    public String state_name;
//    public String district_name;
//    public String block_name;
//    public int lat;
//    public String session_id;
//    public List<String> slots;

    public Session() {
    }

    public Session(int center_id, String name, int pincode, String fee_type, String date, int available_capacity,
                   int available_capacity_dose1, int available_capacity_dose2, String fee, int min_age_limit,
                   String vaccine) {
        this.center_id = center_id;
        this.name = name;
        this.pincode = pincode;
        this.fee_type = fee_type;
        this.date = date;
        this.available_capacity = available_capacity;
        this.available_capacity_dose1 = available_capacity_dose1;
        this.available_capacity_dose2 = available_capacity_dose2;
        this.fee = fee;
        this.min_age_limit = min_age_limit;
        this.vaccine = vaccine;
    }

    public int getCenter_id() {
        return center_id;
    }

    public void setCenter_id(int center_id) {
        this.center_id = center_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAvailable_capacity() {
        return available_capacity;
    }

    public void setAvailable_capacity(int available_capacity) {
        this.available_capacity = available_capacity;
    }

    public int getAvailable_capacity_dose1() {
        return available_capacity_dose1;
    }

    public void setAvailable_capacity_dose1(int available_capacity_dose1) {
        this.available_capacity_dose1 = available_capacity_dose1;
    }

    public int getAvailable_capacity_dose2() {
        return available_capacity_dose2;
    }

    public void setAvailable_capacity_dose2(int available_capacity_dose2) {
        this.available_capacity_dose2 = available_capacity_dose2;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getMin_age_limit() {
        return min_age_limit;
    }

    public void setMin_age_limit(int min_age_limit) {
        this.min_age_limit = min_age_limit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    @Override
    public String toString() {
        return "Session{" +
                "center_id=" + center_id +
                ", name='" + name + '\'' +
                ", pincode=" + pincode +
                ", fee_type='" + fee_type + '\'' +
                ", date='" + date + '\'' +
                ", available_capacity=" + available_capacity +
                ", available_capacity_dose1=" + available_capacity_dose1 +
                ", available_capacity_dose2=" + available_capacity_dose2 +
                ", fee='" + fee + '\'' +
                ", min_age_limit=" + min_age_limit +
                ", vaccine='" + vaccine + '\'' +
                '}';
    }
}
