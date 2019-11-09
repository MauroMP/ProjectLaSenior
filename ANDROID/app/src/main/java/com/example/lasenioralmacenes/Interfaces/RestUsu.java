package com.example.lasenioralmacenes.Interfaces;

import com.example.lasenioralmacenes.Modelos.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestUsu {

    @GET("logeo/{nombre}")
    Call<Usuario> getUsuario(@Path ("nombre") String usuNombre);
}
