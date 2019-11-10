package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lasenioralmacenes.Fragments.DateFragments;
import com.example.lasenioralmacenes.Interfaces.RestMovs;
import com.example.lasenioralmacenes.Interfaces.RestUsu;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Addmov_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Spinner spinnerprod;
    private Spinner spinneralma;
    private Spinner spinnertipo;
    private ImageView btFecha;
    private ImageView btGuardar;
    private Date date;
    private TextView fecha;
    private String[] prods;
    private String[] almas;
    private int dia, mes, ano;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RestMovs restMov = retrofit.create(RestMovs.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmov_);

        spinnerprod = findViewById(R.id.spProd);
        spinneralma = findViewById(R.id.spAlma);
        spinnertipo = findViewById(R.id.spTipo);
        btFecha = findViewById(R.id.btCalendar);
        btGuardar = findViewById(R.id.btGuardarMov);
        fecha = findViewById(R.id.etFecha);


        getProds();
        getAlmas();
        getTipo();

        btFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DateFragments();
                datePicker.show(getSupportFragmentManager(), "date picker");

                }
            }
        );

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarMov();
            }
        });


    }
    private void getProds(){

        Call<String[]> prodnom = restMov.getProdnom();
        prodnom.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Addmov_Activity.this, "Codigo: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
               String[] stpro = response.body();

                   ArrayAdapter listp = new ArrayAdapter(Addmov_Activity.this, android.R.layout.simple_spinner_item, stpro);
        listp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerprod.setAdapter(listp);
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {

            }
        });


    }

    public void getAlmas(){

        Call<String[]> almanom = restMov.getAlmanom();
        almanom.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Addmov_Activity.this, "Codigo: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String [] stalmas = response.body();
                ArrayAdapter lista = new ArrayAdapter(Addmov_Activity.this, android.R.layout.simple_spinner_item, stalmas);
                lista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinneralma.setAdapter(lista);

            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {

            }
        });
    }

    public void getTipo (){
        String [] stipo= {"Perdida", "Movimiento"};
        ArrayAdapter lista = new ArrayAdapter(Addmov_Activity.this, android.R.layout.simple_spinner_item, stipo);
        lista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertipo.setAdapter(lista);

    }


    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        fecha.setText(currentDateString);
        date = c.getTime();

    }

    public void guardarMov(){

        

    }

}
