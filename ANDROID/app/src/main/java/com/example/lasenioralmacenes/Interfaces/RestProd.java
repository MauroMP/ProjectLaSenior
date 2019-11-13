package com.example.lasenioralmacenes.Interfaces;

import com.example.lasenioralmacenes.Modelos.Movimiento;
import com.example.lasenioralmacenes.Modelos.Producto;
import com.example.lasenioralmacenes.Modelos.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestProd {

    @GET("prod/prods")
    Call<List<Producto>> getProds();

    @PUT("prod/del")
    Call<Boolean> delProd(@Body Producto pro);

    @GET("prod/{id}")
    Call<Producto> getProdId(@Path("id") Long id);

    @PUT("prod/update")
    Call<Producto> updateProd(@Body Producto pro);
}
