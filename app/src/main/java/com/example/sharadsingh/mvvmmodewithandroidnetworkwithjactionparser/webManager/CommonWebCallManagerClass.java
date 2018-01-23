package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.webManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.activity.CommonBaseActivity;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.fragments.CommonBaseFragment;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.utills.AppPreference;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.utills.Constants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by root on 10/11/16.
 */
public class CommonWebCallManagerClass<T> extends CommonBaseActivity {
    private static final String TAG = CommonWebCallManagerClass.class.getSimpleName();
    protected ProgressDialog _dialog;
    protected String message = "Loading...";
    Context _context;
    Class<T> _responseType;
    String _requestUrl;
    String methodName;
    private boolean isForFragment;
    private boolean isApiRequired = true;
    private boolean isToShowProgress = true;
    private String apiKey;
    private String request_type;
    private String mode;
    private CommonBaseFragment mFragment;
    JSONObject object;
    int mMODE;
    String url;
    RequestQueue queue;
    T result = null;
    String response = "";
    ObjectMapper mapper;
    public CommonWebCallManagerClass(Context context, String requestUrl, Class<T> responseType, String methodName, String requstType, String Mode, JSONObject obj) {
        _context = context;
        _requestUrl = requestUrl;
        _responseType = responseType;
        this.methodName = methodName;
        this.request_type = requstType;
        this.mode = Mode;
        this.object = obj;

        AndroidNetworking.initialize(_context.getApplicationContext());
        if (mode.equalsIgnoreCase("1")) {
            mMODE = Request.Method.GET;
        } else {
            mMODE = Request.Method.POST;
        }
        url = _requestUrl + this.methodName;
        queue = Volley.newRequestQueue(_context);
        queue.getCache().remove(url);
        queue.getCache().clear();
        AppPreference prefs = new AppPreference(_context.getApplicationContext());
        apiKey = prefs.getStringValueForTag(Constants.API_KEY);
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        CommonVollyFunction(request_type);

        CommonRetrofitFunction(request_type);

        CommonAndroidNetworkWithJactionParserFunction(request_type);

    }

    private void CommonAndroidNetworkWithJactionParserFunction(String request_type) {
        if (request_type.equalsIgnoreCase("object")) {
            if (isToShowProgress) {
                _dialog = ProgressDialog.show(_context, "", message, true);
            }
            if(object == null){
               callWithGetWithPAramiter(url);
            }else {
                callWithJsonBodyWithPost(url,object);

            }
        } else {
            if (isToShowProgress) {
                _dialog = ProgressDialog.show(_context, "", message, true);
            }
        }

    }

    private void callWithJsonBodyWithPost(String url, JSONObject object) {
        AndroidNetworking.post(url)
                .addJSONObjectBody(object)
                .addHeaders("authKey", "27d6POA4EuUPneahXGMhwGWyHK48kAta6somSHOH+e2yX4HGuPEWFU6VSzVquIPx")// posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Responce_data", response.toString());
                        if (_dialog != null) {
                            _dialog.cancel();
                        }
                        try {

                            result = mapper.readValue(response.toString(), _responseType);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (isFragment()) {
                            mFragment.processFragmentResponse(result);
                        } else {
                            ((CommonBaseActivity) _context).processResponse(result);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        if (_dialog != null) {
                            _dialog.cancel();
                        }
                        String response = error.getMessage();

                    }
                });

    }

    private void callWithGetWithPAramiter(String url) {
        AndroidNetworking.get(url)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Responce_data", response.toString());
                        if (_dialog != null) {
                            _dialog.cancel();
                        }
                        try {
                            result = mapper.readValue(response.toString(), _responseType);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (isFragment()) {
                            mFragment.processFragmentResponse(result);
                        } else {
                            ((CommonBaseActivity) _context).processResponse(result);
                        }


                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        if (_dialog != null) {
                            _dialog.cancel();
                        }
                        String response = error.getMessage();
                    }
                });

    }

    private void CommonRetrofitFunction(String request_type) {
        if (request_type.equalsIgnoreCase("object")) {
            if (isToShowProgress) {
                _dialog = ProgressDialog.show(_context, "", message, true);
            }
            if(object == null){
                CallWithBodysomeMethodName(methodName);
            }else {
                CallWithNoBodysomeMethodName(object, methodName);
            }
        } else {
            if (isToShowProgress) {
                _dialog = ProgressDialog.show(_context, "", message, true);
            }
        }
    }





    private void CommonVollyFunction(String request_type) {
        if (request_type.equalsIgnoreCase("object")) {
            if (isToShowProgress) {
                _dialog = ProgressDialog.show(_context, "", message, true);
            }
             gotoJsonObject(queue);
        } else {
            if (isToShowProgress) {
                _dialog = ProgressDialog.show(_context, "", message, true);
            }
             gotoJsonArray(queue);
        }
    }

    private void CallWithNoBodysomeMethodName(JSONObject object, String methodName) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Request.Builder ongoing = chain.request().newBuilder();
                        ongoing.addHeader("Accept", "application/json; versions=1");
                        if (isApiRequired) {
                            ongoing.addHeader("authkey","JKWDOGgIkLNLia38rryFxKL7HEoKeqXSrxoedbjCTZ0+IP5WGZXRHof3wfRBo6am");
                        }
                        return chain.proceed(ongoing.build());
                    }
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOST_URL)
                .client(httpClient)
                .build();

        ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), object.toString());
        Call<ResponseBody> requestCall;
        if(mode.equalsIgnoreCase("1")){
            requestCall = apiCalls.someCallPost(url, body);
        }else {
            requestCall = apiCalls.someCallGet(url, body);
        }
        requestCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                try {
                    String response = rawResponse.body().string();
                    Log.d("Responce_data", response);
                    if (_dialog != null) {
                        _dialog.cancel();
                    }
                    result = new Gson().fromJson(response, _responseType);
                    if (isFragment()) {
                        mFragment.processFragmentResponse(result);
                    } else {
                        ((CommonBaseActivity) _context).processResponse(result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (_dialog != null) {
                    _dialog.cancel();
                }
                String response = throwable.getMessage();
            }
        });

    }

    public void CallWithBodysomeMethodName(String urls) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Request.Builder ongoing = chain.request().newBuilder();
                         ongoing.addHeader("Accept", "application/json; charset=utf-8");
                        if (isApiRequired) {
                          ongoing.addHeader("authKey", "JKWDOGgIkLNLia38rryFxKL7HEoKeqXSrxoedbjCTZ0+IP5WGZXRHof3wfRBo6am");
                        }
                        return chain.proceed(ongoing.build());
                    }
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_HOST_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        Call requestCall;
        if(mode.equalsIgnoreCase("1")){
            requestCall = apiCalls.someCallGetNoBody(url);
        }else {
            requestCall = apiCalls.someCallPostNoBody(url);
         }
         requestCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String re = response.body().toString();
                    String dre = response.body().string();
                    Log.d("Responce_data", re);
                    if (_dialog != null) {
                        _dialog.cancel();
                    }
                    result = new Gson().fromJson(re, _responseType);
                    if (isFragment()) {
                        mFragment.processFragmentResponse(result);
                    } else {
                        ((CommonBaseActivity) _context).processResponse(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (_dialog != null) {
                    _dialog.cancel();
                }
                String response = throwable.getMessage();
            }
        });

    }

    private void gotoJsonArray(RequestQueue queue) {
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(mMODE, url, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resultJson) {
                if (_dialog != null) {
                    _dialog.cancel();
                }

                String resultObj = resultJson.toString();
                result = new Gson().fromJson(response, _responseType);
                if (isFragment()) {
                    mFragment.processFragmentResponse(result);
                } else {
                    ((CommonBaseActivity) _context).processResponse(result);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_dialog != null) {
                    _dialog.cancel();
                }
                System.out.println(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError, NullPointerException {
                Map<String, String> params = new HashMap<>();
                if (apiKey != null)
                    params.put("authKey", apiKey);
                return params;
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(300000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsObjRequest);
    }

    private void gotoJsonObject(RequestQueue queue) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(mMODE, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject resultJson) {
                if (_dialog != null) {
                    _dialog.cancel();
                }
                String resultObj = resultJson.toString();
                result = new Gson().fromJson(response, _responseType);
                if (isFragment()) {
                    mFragment.processFragmentResponse(result);
                } else {
                    ((CommonBaseActivity) _context).processResponse(result);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_dialog != null) {
                    _dialog.cancel();
                }
                System.out.println(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError, NullPointerException {
                Map<String, String> params = new HashMap<>();
               /* if(apiKey !=null)
                    params.put("authKey",apiKey);*/
                return params;
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(300000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsObjRequest);
    }

    public void setIsApiKeyRequired(boolean isReqoured) {
        this.isApiRequired = isReqoured;
    }

    public boolean isFragment() {
        return isForFragment;
    }

    void setFragment(CommonBaseFragment fragment) {
        mFragment = fragment;
    }

    public void setForFragment(boolean isFragment) {
        this.isForFragment = isFragment;
    }

    public void setMessage(String loadingMessage) {
        message = loadingMessage;
    }

    public void setShowProgress(boolean isToShowProgress) {
        this.isToShowProgress = isToShowProgress;
    }


}
