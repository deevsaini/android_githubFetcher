package com.example.github_repos.Api;

import com.example.github_repos.Model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndPoints {
    @GET("/users/{user}/repos")
    Call<List<Repository>> getRepo(@Path("user") String name);
}
