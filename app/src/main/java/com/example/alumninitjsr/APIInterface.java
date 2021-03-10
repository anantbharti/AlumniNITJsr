package com.example.alumninitjsr;

import com.example.alumninitjsr.responses.LoginResponse;
import com.example.alumninitjsr.responses.ProfileResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("login")
    Call<LoginResponse> loginUser(@Body RequestBody requestBody);

    @FormUrlEncoded
    @POST("register")
    Call<LoginResponse> register(@Field("username") String username,
                                    @Field("password") String pwd,
                                    @Field("batch") String batch,
                                    @Field("branch") String branch,
                                    @Field("name") String name,
                                    @Field("email") String email,
                                    @Field("user_type") String userType,
                                    @Field("linkedin") String linkedIn,
                                    @Field("dob") String dob,
                                    @Field("hometown") String hometown,
                                    @Field("mobile") String mobile);
    @POST("AlumniProfile")
    Call<ProfileResponse> alumniProfile(@Body RequestBody requestBody);

    @POST("MyProfile")
    Call<ProfileResponse> myProfile(@Body RequestBody requestBody);
}
