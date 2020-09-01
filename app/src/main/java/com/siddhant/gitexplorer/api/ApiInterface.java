package com.siddhant.gitexplorer.api;

import com.siddhant.gitexplorer.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET(ApiEndPoints.ENDPOINT_REPOSITORIES)
    Call<List<Repository>> popularRepositories();


}
