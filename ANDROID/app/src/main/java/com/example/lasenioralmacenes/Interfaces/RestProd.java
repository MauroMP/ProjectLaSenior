package com.example.lasenioralmacenes.Interfaces;

import com.example.lasenioralmacenes.Modelos.Almacenamiento;
import com.example.lasenioralmacenes.Modelos.Familia;
import com.example.lasenioralmacenes.Modelos.Movimiento;
import com.example.lasenioralmacenes.Modelos.Producto;
import com.example.lasenioralmacenes.Modelos.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestProd {

    @GET("prod/prods")
    Call<List<Producto>> getProds();

    @PUT("prod/del")
    Call<Boolean> delProd(@Body Producto pro);

    @GET("prod/{nombre}")
    Call<Producto> getProdId(@Path("nombre") String nombre);

    @PUT("prod/update")
    Call<Producto> updateProd(@Body Producto pro);

    @POST("prod/addpro")
    Call<Producto> crearPro(@Body Producto producto);

    @GET("mov/obtnom")
    Call<String[]> getAlmanom();

    @GET("prod/familianom")
    Call<String[]> getFaminom();

    @GET ("prod/obtalma/{nombre}")
    Call<Almacenamiento> getAlma (@Path("nombre") String nombre);

    @GET ("prod/obtfami/{nombre}")
    Call<Familia> getFami (@Path("nombre") String nombre);




}
