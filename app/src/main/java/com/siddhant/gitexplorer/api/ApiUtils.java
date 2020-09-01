package com.siddhant.gitexplorer.api;

public class ApiUtils {

    private static final String BASE_URL = "https://api.github.com";


    public static ApiInterface getApiCalls() {
        return RetrofitUtil.getRetrofit(BASE_URL).create(ApiInterface.class);
    }

}
