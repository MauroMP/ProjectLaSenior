package com.example.lasenioralmacenes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.lasenioralmacenes.Interfaces.RestProd;
import com.example.lasenioralmacenes.Modelos.Producto;
import com.example.lasenioralmacenes.Modelos.Usuario;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrdActivity extends AppCompatActivity {
    private TextView idp;
    private TextView nombre;
    private TextView precio;
    private TextView peso;
    private TextView fvenc;
    private TextView palma;
    private TextView pfami;
    private TextView stockT;
    private ImageView del;
    private ImageView save;
    private ImageView update;
    private ImageView btAtras;
    Retrofit retrofit;
    RestProd restprod;
    Producto producto;
    boolean borrado;
    Usuario usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produ);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        usu = (Usuario)bundle.getSerializable("Usuario");

        idp = findViewById(R.id.eTid);
        nombre = findViewById(R.id.eTNombre);
        precio = findViewById(R.id.eTPrecio);
        peso = findViewById(R.id.eTPeso);
        fvenc = findViewById(R.id.eTFechVen);
        palma = findViewById(R.id.eTAlma);
        pfami = findViewById(R.id.eTFami);
        stockT = findViewById(R.id.eTSTotal);
        del = findViewById(R.id.btdel);
        btAtras = findViewById(R.id.btAtras10);
        //save = findViewById(R.id.btsave);

        final Producto prod = (Producto) bundle.getSerializable("prod_nom");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restprod = retrofit.create(RestProd.class);

        Call<Producto> dpro = restprod.getProdId(prod.getProdNombre());

        dpro.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(PrdActivity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                producto = response.body();
                idp.setText(String.format("%s", producto.getProdId()));
                nombre.setText(String.format("%s", producto.getProdNombre()));
                precio.setText(String.format("%s", producto.getProdPrecio()));
                peso.setText(String.format("%s", producto.getProdPeso()));
                fvenc.setText(String.format("%s", producto.getProdFven()));
                palma.setText(String.format("%s", producto.getAlmacenamiento().getAlmaNombre()));
                pfami.setText(String.format("%s", producto.getFamilia().getFamiNombre()));
                stockT.setText(String.format("%s", producto.getProdStktotal()));
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

            }
        });

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrdActivity.this, activity_Producto.class);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
                finish();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(PrdActivity.this, "Producto: " + producto.getProdId().toString(), Toast.LENGTH_LONG).show();
                deleteProd(producto);

            }
        });

        /*save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Producto> dpro = restprod.updateProd(prod);

                dpro.enqueue(new Callback<Producto>() {
                    @Override
                    public void onResponse(Call<Producto> call, Response<Producto> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(PrdActivity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        Toast.makeText(PrdActivity.this, "Producto actualizado", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Producto> call, Throwable t) {

                    }
                });

            }
        });*/
    }


    public void deleteProd(Producto produ) {

        Call<Boolean> delpro = restprod.delProd(produ);
        delpro.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                borrado = false;
                if (!response.isSuccessful()) {
                    Toast.makeText(PrdActivity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(PrdActivity.this, "Producto Eliminado", Toast.LENGTH_LONG).show();
                borrado = true;
                Intent intent = new Intent(PrdActivity.this, activity_Producto.class);
                intent.putExtra("mensaje", "Producto Eliminado");
                intent.putExtra("Usuario",usu);
                startActivity(intent);
                finish();

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(PrdActivity.this, "Producto: " + producto.getProdId().toString() + "no borrado" + t, Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(MainActivity.this, "Por favor ingrese credenciales", Toast.LENGTH_LONG).show();
        //finish();
    }
}
