package com.siddhant.gitexplorer.api;

public class ApiUtils {

    private static final String BASE_URL = "https://api.github.com";

    public static final String API_TOKEN = "46c5c8238ae0aca146fdeb88c4be94b9c19a8480";

    public static ApiInterface getApiCalls() {
        return RetrofitUtil.getRetrofit(BASE_URL).create(ApiInterface.class);
    }
}
