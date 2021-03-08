package com.example.alumninitjsr;

import com.example.alumninitjsr.responses.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @POST("login")
    Call<LoginResponse> loginUser(@Body HashMap<String,String> loginRequest);
}
