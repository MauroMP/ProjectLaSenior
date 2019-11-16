package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lasenioralmacenes.Interfaces.RestAlmas;
import com.example.lasenioralmacenes.Modelos.Almacenamiento;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlmaActivity extends AppCompatActivity {

    private TextView ida;
    private TextView nombre;
    private TextView volumen;
    private TextView cantes;
    private TextView capPeso;
    private TextView costoOp;
    private TextView descripA;
    private TextView locale;
    private ImageView btAtras;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RestAlmas restAlmas = retrofit.create(RestAlmas.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alma);

        ida = findViewById(R.id.idAlma);
        nombre = findViewById(R.id.almaNomView);
        volumen = findViewById(R.id.almaVol);
        cantes = findViewById(R.id.almaCantEs);
        capPeso = findViewById(R.id.almaCapPeso);
        costoOp = findViewById(R.id.CostoOp);
        descripA = findViewById(R.id.txtDescrip);
        locale = findViewById(R.id.aLocale);
        btAtras = findViewById(R.id.btAtrasA);

        Bundle bundle;
        bundle = getIntent().getExtras();

        final Almacenamiento almad = (Almacenamiento)bundle.getSerializable("almaNombre");

        Call<Almacenamiento> almacenamientoCall = restAlmas.getAlma(almad.getAlmaNombre());
        almacenamientoCall.enqueue(new Callback<Almacenamiento>() {
            @Override
            public void onResponse(Call<Almacenamiento> call, Response<Almacenamiento> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(AlmaActivity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                Almacenamiento alma =  response.body();

                ida.setText(String.format("%s",alma.getAlmaId()));
                nombre.setText(alma.getAlmaNombre());
                volumen.setText(String.format("%s",alma.getAlmaVolumen()));
                cantes.setText(String.format("%s",alma.getAlmaCanestiba()));
                capPeso.setText(String.format("%s",alma.getAlmaCappeso()));
                costoOp.setText(String.format("%s",alma.getAlmaCostoop()));
                descripA.setText(alma.getAlmaDescripcion());
                locale.setText(alma.getLocale().getCiudade().getCiuNombre());


            }

            @Override
            public void onFailure(Call<Almacenamiento> call, Throwable t) {

            }
        });
        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlmaActivity.this, Almacenamientos_Activity.class);
                startActivity(intent);
            }
        });





    }
}
