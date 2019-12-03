package com.example.lasenioralmacenes;

import android.content.Intent;
import android.os.Bundle;

import com.example.lasenioralmacenes.Adapters.ProductosAdapter;
import com.example.lasenioralmacenes.Interfaces.RestProd;

import com.example.lasenioralmacenes.Modelos.Producto;
import com.example.lasenioralmacenes.Modelos.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class activity_Producto extends AppCompatActivity {

    private ImageView btatras;
    private ListView listaprod;
    private Usuario usu;
    private ImageView btCrear;
    public static String nomusup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        btatras = findViewById(R.id.btAtrasA3);
        listaprod = findViewById(R.id.listprods);
        btCrear = findViewById(R.id.btnNvoProd);


        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        usu = (Usuario) bundle.getSerializable("Usuario");
       // String in = (String)bundle.getSerializable("mensaje");
       // String ex = (String)bundle.getSerializable("msgCrear");
        nomusup = usu.getUsuNombre();
        ObtTodosProd();

        /*buscarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObtTodosProd();
            }

        });*/

        btatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_Producto.this, Menu_Activity.class);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
                finish();
            }
        });


        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_Producto.this, activity_altaprodu.class);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
                finish();
            }
        });

    }

    private void ObtTodosProd() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestProd restProd = retrofit.create(RestProd.class);

        Call<List<Producto>> lprod = restProd.getProds();

        lprod.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(activity_Producto.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Producto> listprod;
                listprod =  response.body();
                listaprod.setAdapter(new ProductosAdapter(activity_Producto.this,R.layout.row_productos,listprod));

            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(MainActivity.this, "Por favor ingrese credenciales", Toast.LENGTH_LONG).show();
        //finish();
    }

}
