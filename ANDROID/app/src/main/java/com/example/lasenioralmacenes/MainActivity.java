package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private TextView jsonTextview;
    private TextView NombreUsu;
    private TextView PassUsu;
    private Button inicioS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonTextview = findViewById(R.id.jsonText);
        NombreUsu =findViewById(R.id.Nomusu);
        PassUsu = findViewById(R.id.Pass);
        inicioS = findViewById(R.id.buttonIS);


        inicioS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NombreUsu.getText().toString().trim().length() == 0 || PassUsu.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Debe ingresar un nombre de usuario y contraseña", Toast.LENGTH_SHORT).show();

                } else {
                    String m = NombreUsu.getText().toString();
                    Toast.makeText(MainActivity.this, m, Toast.LENGTH_LONG).show();
                    getUsu(m);
                }

            }
         } );

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
                    jsonTextview.setText("Codigo" + response.code());
                return;
                }
                Usuario usu = response.body();
                if(!usu.getUsuNombre().equals("Usuario")){
                    if(usu.getUsuPassword().equals(PassUsu.getText().toString())) {

                        jsonTextview.setText(usu.getUsuNombre() + "\n"
                                + usu.getUsuApellido() + "\n" + usu.getUsuCorreo());
                        Intent intent = new Intent(MainActivity.this, Menu_Activity.class);
                        intent.putExtra("Usuario", usu);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Bienvenido " + usu.getUsuNombre(), Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Usuario: " + NombreUsu.getText().toString() + " incorrecto", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                jsonTextview.setText(t.getMessage());
            }
        });





    }





}
