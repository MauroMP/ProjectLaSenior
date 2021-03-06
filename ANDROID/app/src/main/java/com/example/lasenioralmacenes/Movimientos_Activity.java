package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lasenioralmacenes.Adapters.MovimientosAdapter;
import com.example.lasenioralmacenes.Interfaces.RestMovs;
import com.example.lasenioralmacenes.Modelos.Movimiento;
import com.example.lasenioralmacenes.Modelos.Usuario;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Movimientos_Activity extends AppCompatActivity {

    private ImageView btmovs;
    ListView listmovs;
    Usuario usu;
    private ImageView btcrear;
    private ImageView btatras;
    public static String nomusum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos_);

        //btmovs = findViewById(R.id.btListmovs);
        listmovs = findViewById(R.id.listmov);
        btcrear = findViewById(R.id.btCrear);
        btatras = findViewById(R.id.btAtrasA2);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        usu = (Usuario)bundle.getSerializable("Usuario");
        String in = (String)bundle.getSerializable("mensaje");
        nomusum = usu.getUsuNombre();

        Toast.makeText(Movimientos_Activity.this, in, Toast.LENGTH_LONG).show();

        listarMovs();

        /*btmovs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarMovs();

            }
        });*/

        btcrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Movimientos_Activity.this, Addmov_Activity.class);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
                finish();

            }
        });
        btatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Movimientos_Activity.this, Menu_Activity.class);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
                finish();
            }
        });



    }

    private void listarMovs(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestMovs restMovs = retrofit.create(RestMovs.class);

        Call<List<Movimiento>> lmovs = restMovs.getMovs();

        lmovs.enqueue(new Callback<List<Movimiento>>() {
            @Override
            public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Movimientos_Activity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Movimiento> listmov;
                      listmov =  response.body();
                      listmovs.setAdapter(new MovimientosAdapter(Movimientos_Activity.this,R.layout.row_movimientos,listmov));

            }

            @Override
            public void onFailure(Call<List<Movimiento>> call, Throwable t) {

            }
        });

    }


}
