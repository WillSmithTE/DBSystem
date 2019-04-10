package com.willsmithte.DBSystemBE.model;

import javax.persistence.Id;

/**
 * Created by Will Smith on 16/3/19.
 */

@javax.persistence.Entity
public class Location {

    @Id
    private int id;
    private String country;
    private String city;
    private int postcode;

    public Location(String country, String city, int postcode) {
        this.country = country;
        this.city = city;
        this.postcode = postcode;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
}
