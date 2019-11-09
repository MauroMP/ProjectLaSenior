package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;

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
    private ImageView del;
    private ImageView save;
    private ImageView update;
    Retrofit retrofit;
    RestMovs restMov;
    Movimiento movimiento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mov);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        tidMov = findViewById(R.id.idMov);
        tcosto = findViewById(R.id.Costo);
        tcant = findViewById(R.id.cantidad);
        tprod = findViewById(R.id.Producto);
        talma = findViewById(R.id.Almacen);
        ttipo = findViewById(R.id.tipo);
        del = findViewById(R.id.btdel);
        save = findViewById(R.id.btsave);

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

            }

            @Override
            public void onFailure(Call<Movimiento> call, Throwable t) {

            }
        });


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Movimiento> delmov = restMov.delMov(movimiento);
                delmov.enqueue(new Callback<Movimiento>() {
                    @Override
                    public void onResponse(Call<Movimiento> call, Response<Movimiento> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(MovActivity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        Toast.makeText(MovActivity.this, "Movimiento eliminado", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Movimiento> call, Throwable t) {

                    }


                });


                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Call<Movimiento> dmov = restMov.updateMov(mov);

                        dmov.enqueue(new Callback<Movimiento>() {
                            @Override
                            public void onResponse(Call<Movimiento> call, Response<Movimiento> response) {
                                if (!response.isSuccessful()){
                                    Toast.makeText(MovActivity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                                    return;
                                }
                                Toast.makeText(MovActivity.this, "Movimiento actualizado", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(Call<Movimiento> call, Throwable t) {

                            }
                        });

                    }
                });
            }






        });



    }
}
