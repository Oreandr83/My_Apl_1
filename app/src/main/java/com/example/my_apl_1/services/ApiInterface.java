package com.example.my_apl_1.services;

import com.example.my_apl_1.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("photos")
    Call<List<Photo>> getPhotos(

            @Query("client_id") String id
    );

    @GET("photos/{id}")
    Call<Photo> getDetailPhoto(

            @Query("client_id") String detailId,
            @Path("id") String detId
    );
}
