package com.example.demo_android_api.Services;

import com.example.demo_android_api.Models.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlayerService
{
    @GET("players")
    Call<List<Player>> getPlayers();

    @GET("players/{id}")
    Call<Player> getPlayer(@Path("id") int id);

    @POST("players")
    Call<Player> createPlayer(@Body Player player);

    @PUT("players/{id}")
    Call<Player> updatePlayer(@Path("id") int id, @Body Player player);

    @DELETE("players/{id}")
    Call<Void> deletePlayer(@Path("id") int id);

}
