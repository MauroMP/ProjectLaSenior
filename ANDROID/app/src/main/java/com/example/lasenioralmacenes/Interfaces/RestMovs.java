package com.example.lasenioralmacenes.Interfaces;

import com.example.lasenioralmacenes.Modelos.Movimiento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestMovs {

    @GET("mov/movs")
    Call<List<Movimiento>> getMovs();

    @PUT("mov/del")
    Call<Boolean> delMov(@Body Movimiento mov);

    @PUT("mov/update")
    Call<Movimiento> updateMov(@Body Movimiento mov);

    @GET("mov/{id}")
    Call<Movimiento> getMovId(@Path("id") Long id);

    @POST("mov/nvomov")
    Call<Movimiento> crearMov(@Body Movimiento movimiento);

    @GET("prod/obtnom")
    Call<String[]> getProdnom();

    @GET("mov/obtnom")
    Call<String[]> getAlmanom();





}
