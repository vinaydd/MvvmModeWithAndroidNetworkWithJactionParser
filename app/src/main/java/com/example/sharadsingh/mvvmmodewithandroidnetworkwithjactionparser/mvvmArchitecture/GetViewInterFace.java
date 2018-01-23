package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture;

import java.util.ArrayList;

/**
 * Created by sharadsingh on 04/12/17.
 */

public interface GetViewInterFace<T> {
      void getDataView(ArrayList<T> values);
      void SetViewData(ArrayList<T> values);
}
