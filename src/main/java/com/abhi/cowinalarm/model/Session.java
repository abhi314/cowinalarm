package com.abhi.cowinalarm.model;

import java.util.List;

public class Session {
    public int center_id;
    public String name;
    public String address;
    public String state_name;
    public String district_name;
    public String block_name;
    public int pincode;
    public String from;
    public String to;
    public int lat;
    public String fee_type;
    public String session_id;
    public String date;
    public int available_capacity;
    public String fee;
    public int min_age_limit;
    public String vaccine;
    public List<String> slots;

    public Session() {
    }

    public Session(int center_id, String name, String address, String state_name, String district_name, String block_name, int pincode, String from, String to, int lat, String fee_type, String session_id, String date, int available_capacity, String fee, int min_age_limit, String vaccine, List<String> slots) {
        this.center_id = center_id;
        this.name = name;
        this.address = address;
        this.state_name = state_name;
        this.district_name = district_name;
        this.block_name = block_name;
        this.pincode = pincode;
        this.from = from;
        this.to = to;
        this.lat = lat;
        this.fee_type = fee_type;
        this.session_id = session_id;
        this.date = date;
        this.available_capacity = available_capacity;
        this.fee = fee;
        this.min_age_limit = min_age_limit;
        this.vaccine = vaccine;
        this.slots = slots;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getBlock_name() {
        return block_name;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
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

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "\nSession{" +
                "\ncenter_id=" + center_id +
                ", \nname='" + name + '\'' +
                ", \npincode=" + pincode +
                ", \ndate='" + date + '\'' +
                ", \navailable_capacity=" + available_capacity +
                ", \nfee='" + fee + '\'' +
                ", \nmin_age_limit=" + min_age_limit +
                ", \nvaccine='" + vaccine + '\'' +
                ", \nslots=" + slots +
                '}';
    }
}
