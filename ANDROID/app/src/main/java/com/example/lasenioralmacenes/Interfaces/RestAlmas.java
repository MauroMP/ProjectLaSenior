package com.example.lasenioralmacenes.Interfaces;

import com.example.lasenioralmacenes.Modelos.Almacenamiento;
import com.example.lasenioralmacenes.Modelos.Movimiento;
import com.example.lasenioralmacenes.Modelos.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestAlmas {

    @GET("alma/almas")
    Call<List<Almacenamiento>> getAlmas();

    @GET("alma/{id}/{id1}")
    Call<List<Almacenamiento>> getAlmasId(@Path("id") Long id, @Path("id1") Long id1);

    @GET("alma/{nombre}")
    Call<Almacenamiento> getAlma(@Path("nombre") String nombre);





}
