package com.example.alumninitjsr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
        private static String BASE_URL = "https://pseudohack.in/AlumniApi/";
        private static Retrofit retrofit;
        private static Gson gson;

        public static Retrofit getRetrofit(){
            HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

            if(retrofit==null){
                gson=new GsonBuilder().setLenient().create();
                retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                        .client(okHttpClient)
                        .build();
            }
            return retrofit;
        }
        public static APIInterface getApiInterface(){
            APIInterface apiInterface = getRetrofit().create(APIInterface.class);
            return  apiInterface;
        }
}
