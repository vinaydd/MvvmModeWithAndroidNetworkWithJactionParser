package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.citystatelistviewmvvm;

import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.model.CountryStateCityListModel;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.responce.BaseResponse;

/**
 * Created by sharadsingh on 26/12/17.
 */

public class CityListResponse extends BaseResponse {
    private CityListModel[] data;

    public CityListModel[] getData() {
        return data;
    }

    public CityListResponse setData(CityListModel[] data) {
        this.data = data;
        return this;
    }


}
