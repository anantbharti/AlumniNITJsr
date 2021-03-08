package com.example.alumninitjsr;

import com.example.alumninitjsr.responses.LoginResponse;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @POST("login")
    Call<LoginResponse> loginUser(@Body RequestBody requestBody);
}
