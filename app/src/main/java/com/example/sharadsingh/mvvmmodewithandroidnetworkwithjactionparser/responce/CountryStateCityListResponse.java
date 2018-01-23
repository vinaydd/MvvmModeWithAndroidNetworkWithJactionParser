package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.responce;

import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.model.CountryStateCityListModel;

/**
 * Created by sharadsingh on 26/12/17.
 */

public class CountryStateCityListResponse extends BaseResponse {
    private CountryStateCityListModel[] data;

    public CountryStateCityListModel[] getData() {
        return data;
    }

    public CountryStateCityListResponse setData(CountryStateCityListModel[] data) {
        this.data = data;
        return this;
    }


}
