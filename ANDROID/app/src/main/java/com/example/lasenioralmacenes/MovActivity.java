package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lasenioralmacenes.Interfaces.RestMovs;
import com.example.lasenioralmacenes.Modelos.Movimiento;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovActivity extends AppCompatActivity {

    private TextView tidMov;
    private TextView tcosto;
    private TextView tcant;
    private TextView tprod;
    private TextView talma;
    private TextView ttipo;
    private TextView txDescrip;
    private ImageView del;
    private ImageView btAtras;
    Retrofit retrofit;
    RestMovs restMov;
    Movimiento movimiento;
    boolean borrado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mov);

        Bundle bundle;
        bundle = getIntent().getExtras();

        tidMov = findViewById(R.id.idMov);
        tcosto = findViewById(R.id.CostoOp);
        tcant = findViewById(R.id.almaCapPeso);
        tprod = findViewById(R.id.aLocale);
        talma = findViewById(R.id.almaVol);
        ttipo = findViewById(R.id.almaCantEs);
        txDescrip = findViewById(R.id.txtDescrip);
        del = findViewById(R.id.btdel);
        btAtras = findViewById(R.id.btAtrasA);


        final Movimiento mov = (Movimiento)bundle.getSerializable("movid");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restMov = retrofit.create(RestMovs.class);


        Call<Movimiento> dmov = restMov.getMovId(mov.getMovId());
        dmov.enqueue(new Callback<Movimiento>() {
            @Override
            public void onResponse(Call<Movimiento> call, Response<Movimiento> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MovActivity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
               movimiento = response.body();
                tidMov.setText(String.format("%s",movimiento.getMovId()));
                tcosto.setText(String.format("%s",movimiento.getMovCosto()));
                tcant.setText(String.format("%s",movimiento.getMovCantidad()));
                tprod.setText(movimiento.getProducto().getProdNombre());
                talma.setText(movimiento.getAlmacenamiento().getAlmaNombre());
                ttipo.setText(movimiento.getMovTipo());
                txDescrip.setText(movimiento.getMovDescripcion());

            }

            @Override
            public void onFailure(Call<Movimiento> call, Throwable t) {

            }
        });

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovActivity.this, Movimientos_Activity.class);
                startActivity(intent);
            }
        });


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MovActivity.this, "Movimiento: " + movimiento.getMovId().toString(), Toast.LENGTH_LONG).show();
                deleteMov(movimiento);

            }
                });



    }

    public void deleteMov (Movimiento movim){

        Call<Boolean> delmov = restMov.delMov(movim);
        delmov.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                borrado = false;
                if (!response.isSuccessful()) {
                    Toast.makeText(MovActivity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText( MovActivity.this, "Movimiento eliminado", Toast.LENGTH_LONG).show();
                borrado = true;
                Intent intent = new Intent(MovActivity.this, Movimientos_Activity.class);
                intent.putExtra("mensaje", "Movimiento Eliminado");
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(MovActivity.this, "Movimiento: " + movimiento.getMovId().toString() + "no borrado" + t, Toast.LENGTH_LONG).show();

            }
        });

    }







}
