package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.model;

import java.io.Serializable;

/**
 * Created by sharadsingh on 26/12/17.
 */

public class CountryStateCityListModel implements Serializable {
    private  String cityId;
    private  String countryName;
    private  String cityName;
    private  String stateName;
    private  String countryId;
    private  String stateId;

    public String getCityId() {
        return cityId;
    }

    public CountryStateCityListModel setCityId(String cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public CountryStateCityListModel setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public CountryStateCityListModel setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getStateName() {
        return stateName;
    }

    public CountryStateCityListModel setStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public CountryStateCityListModel setCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getStateId() {
        return stateId;
    }

    public CountryStateCityListModel setStateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

}
