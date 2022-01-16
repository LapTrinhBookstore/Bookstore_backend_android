package com.example.appbookstore.api;
import com.example.appbookstore.DanhGiaObj;
import com.example.appbookstore.UsersModel;
import com.example.appbookstore.bankAccountModel;
import com.example.appbookstore.model.DetailBook;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    String urlThuan = "http://192.168.178.48/Bookstore_android/public/api/";
    String urlThien = "http://192.168.1.7/android/Bookstore/public/api/";


    ApiService apiService = new Retrofit.Builder()
            .baseUrl(urlThien)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("users/{user}")
    Call<UsersModel> getUsers(@Path("user") int id);

    @POST("users/updatePhoneNumber")
    Call<Integer> updatePhoneNumber(@Body UsersModel users);

    @POST("users/updateEmail")
    Call<Integer> updateEmail(@Body UsersModel users);

    @POST("users/updatePassword")
    Call<Integer> updatePassword(@Body UsersModel users);

    @POST("users/updateDetails")
    Call<Integer> updateDetails(@Body UsersModel users);

    @POST("saveBankAccount")
    Call<Boolean> saveBankAccount(@Body bankAccountModel bankAccount);

    @POST("product/detail")
    Call<DetailBook> getBook(@Query("idUser") int idUser, @Query("idProduct") int idProduct);

    @POST("product/ratings")
    Call<List<DanhGiaObj>> getTopRating(@Query("id")  int idProduct);
}
