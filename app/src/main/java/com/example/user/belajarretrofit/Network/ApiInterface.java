package com.example.user.belajarretrofit.Network;

import com.example.user.belajarretrofit.Model.Konsumen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/sisteminformasi/cobajson.php")
    Call<List<Konsumen>> getUser();

    @FormUrlEncoded
    @POST("/sisteminformasi/insertJson.php")
    Call<Konsumen> postData(@Field("namaKonsumen") String namakonsumen,
                            @Field("alamatkonsumen") String alamatkonsumen);

    @FormUrlEncoded
    @POST("/sisteminformasi/updatedatajson.php")
    Call<Konsumen> updateData(@Field("idkonsumen") String idKonsumen,
                            @Field("namakonsumen") String namakonsumen,
                            @Field("alamatkonsumen") String alamatkonsumen);

    @FormUrlEncoded
    @POST("/sisteminformasi/hapusApi.php")
    Call<Konsumen> deleteData(@Field("idkonsumen") String idKonsumen);


}
