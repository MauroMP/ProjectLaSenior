package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lasenioralmacenes.Adapters.AlmacenamientosAdapter;
import com.example.lasenioralmacenes.Interfaces.RestAlmas;
import com.example.lasenioralmacenes.Modelos.Almacenamiento;
import com.example.lasenioralmacenes.Modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Almacenamientos_Activity extends AppCompatActivity {

    private Button btalmas;
    private ImageView btBuscar;
    private ImageView btClean;
    private ListView listalmas;
    private EditText idI;
    private EditText idF;
    private Usuario usu;
    private Long idini;
    private Long idfin;
    private String idi ;
    private String idf ;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RestAlmas restAlmas = retrofit.create(RestAlmas.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacenamientos_);

        btalmas = findViewById(R.id.btListalmas);
        btClean = findViewById(R.id.btClean);
        listalmas = findViewById(R.id.listalma);
        btBuscar = findViewById(R.id.btBusca);
        idI = findViewById(R.id.IdIni);
        idF = findViewById(R.id.idFinal);


        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        usu = (Usuario) bundle.getSerializable("Usuario");
        String in = (String)bundle.getSerializable("mensaje");
        String ex = (String)bundle.getSerializable("msgCrear");

        btalmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarAlmas();

            }
        });

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serachA();
            }
        });

        btClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Almacenamiento> listalma = new ArrayList<>();
                listalmas.setAdapter(new AlmacenamientosAdapter(Almacenamientos_Activity.this,R.layout.row_almacenamientos,listalma));
                Toast.makeText(Almacenamientos_Activity.this, "Limpieza", Toast.LENGTH_LONG).show();
            }
        });



    }

    private void listarAlmas(){


        Call<List<Almacenamiento>> lalmas = restAlmas.getAlmas();

        lalmas.enqueue(new Callback<List<Almacenamiento>>() {
            @Override
            public void onResponse(Call<List<Almacenamiento>> call, Response<List<Almacenamiento>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Almacenamientos_Activity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Almacenamiento> listalma;
                listalma =  response.body();
                listalmas.setAdapter(new AlmacenamientosAdapter(Almacenamientos_Activity.this,R.layout.row_almacenamientos,listalma));

            }

            @Override
            public void onFailure(Call<List<Almacenamiento>> call, Throwable t) {

            }
        });

    }

    private void serachA(){
        idi = idI.getText().toString();
        idf = idF.getText().toString();


        if(idi.equals("")){
            idi = "0";
        }
        if(idf.equals("")){
            idf = "99999";
        }
        idini = Long.parseLong(idi);
        idfin = Long.parseLong(idf);
        if (idini<0 || idfin<0){
            Toast.makeText(Almacenamientos_Activity.this, "Los ids no son correctos", Toast.LENGTH_LONG).show();
            return;
        }else {
            if (idini > idfin) {
                Toast.makeText(Almacenamientos_Activity.this, "Id Incial debe ser menor que Id Final", Toast.LENGTH_LONG).show();
                return;
            } else {
                idini = Long.parseLong(idi);
                idfin = Long.parseLong(idf);
                buscarAlmas(idini, idfin);
            }
        }

    }

    private void buscarAlmas(Long idini, Long idfin){

        Call<List<Almacenamiento>> lalmas = restAlmas.getAlmasId(idini,idfin);

        lalmas.enqueue(new Callback<List<Almacenamiento>>() {
            @Override
            public void onResponse(Call<List<Almacenamiento>> call, Response<List<Almacenamiento>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Almacenamientos_Activity.this, "Codigo" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Almacenamiento> listalma;
                listalma =  response.body();
                if(listalma.isEmpty()){
                    Toast.makeText(Almacenamientos_Activity.this, "No se encontraron Almacenes en el rango seleccionado", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    listalmas.setAdapter(new AlmacenamientosAdapter(Almacenamientos_Activity.this, R.layout.row_almacenamientos, listalma));
                }

            }

            @Override
            public void onFailure(Call<List<Almacenamiento>> call, Throwable t) {

            }
        });


    }
}
