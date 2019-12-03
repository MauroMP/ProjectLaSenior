package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lasenioralmacenes.Interfaces.RestUsu;
import com.example.lasenioralmacenes.Modelos.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private TextView NombreUsu;
    private TextView PassUsu;
    private ImageView inicioS;
    private ImageView btclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NombreUsu =findViewById(R.id.Nomusu);
        PassUsu = findViewById(R.id.Pass);
        inicioS = findViewById(R.id.buttonIS);
        btclose = findViewById(R.id.close);


        inicioS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NombreUsu.getText().toString().trim().length() == 0 || PassUsu.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Debe ingresar un nombre de usuario y contraseña", Toast.LENGTH_SHORT).show();

                } else {
                    String m = NombreUsu.getText().toString();
                    //Toast.makeText(MainActivity.this, m, Toast.LENGTH_LONG).show();
                    getUsu(m);
                }

            }
         } );

        btclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }

    private void getUsu(String mn){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestUsu restUsu = retrofit.create(RestUsu.class);

        Call<Usuario> usuarioCall = restUsu.getUsuario(mn);

        usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(!response.isSuccessful()){

                    Toast.makeText(MainActivity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                return;
                }
                Usuario usu = response.body();
                if(!usu.getUsuNombre().equals("Usuario")){
                    if(usu.getUsuPassword().equals(PassUsu.getText().toString())) {

                        Intent intent = new Intent(MainActivity.this, Menu_Activity.class);
                        intent.putExtra("Usuario", usu);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Bienvenido " + usu.getUsuNombre(), Toast.LENGTH_LONG).show();
                        finish();

                    }else{
                        Toast.makeText(MainActivity.this, "Usuario o Contraseña incorrecta", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o Cantraseña incorrecta", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });





    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(MainActivity.this, "Por favor ingrese credenciales", Toast.LENGTH_LONG).show();
       // finish();
    }



}
