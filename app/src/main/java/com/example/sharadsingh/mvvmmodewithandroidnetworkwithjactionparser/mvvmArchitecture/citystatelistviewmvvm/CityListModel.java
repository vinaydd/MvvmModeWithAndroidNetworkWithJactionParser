package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.citystatelistviewmvvm;

import java.io.Serializable;

/**
 * Created by sharadsingh on 26/12/17.
 */

public class CityListModel implements Serializable {
    private  String cityId;
    private  String countryName;
    private  String cityName;
    private  String stateName;
    private  String countryId;
    private  String stateId;

    public String getCityId() {
        return cityId;
    }

    public CityListModel setCityId(String cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public CityListModel setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public CityListModel setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getStateName() {
        return stateName;
    }

    public CityListModel setStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public CityListModel setCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getStateId() {
        return stateId;
    }

    public CityListModel setStateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

}
