package com.example.appbookstore.api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.appbookstore.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.178.48/Bookstore_android/public/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("users/{user}")
    Call<Users> getUsers(@Path("user") int id);

    @POST("users/updatePhoneNumber")
    Call<Integer> updatePhoneNumber(@Body Users users);

    @POST("users/update")
    Call<Users> updateUser(@Body Users users);
}
