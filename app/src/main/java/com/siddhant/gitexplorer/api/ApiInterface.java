package com.siddhant.gitexplorer.api;

import com.siddhant.gitexplorer.model.Owner;
import com.siddhant.gitexplorer.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.siddhant.gitexplorer.api.ApiEndPoints.ENDPOINT_REPOSITORIES;

public interface ApiInterface {

    @GET(ENDPOINT_REPOSITORIES)
    Call<List<Repository>> popularRepositories();

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Owner>> repoContributors(@Path("owner") String fullName, @Path("repo") String repoName);

    @GET("/users/{owner}/repos")
    Call<List<Repository>> contributorsRepo(@Path("owner") String name);
}
