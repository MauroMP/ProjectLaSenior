package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lasenioralmacenes.Fragments.DateFragments;
import com.example.lasenioralmacenes.Interfaces.RestMovs;
import com.example.lasenioralmacenes.Interfaces.RestUsu;
import com.example.lasenioralmacenes.Modelos.Almacenamiento;
import com.example.lasenioralmacenes.Modelos.Movimiento;
import com.example.lasenioralmacenes.Modelos.Producto;

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
    private EditText cant;
    private EditText descip;
    private String[] prods;
    private String[] almas;
    private int dia, mes, ano;
    private String sprod;
    private Double canti;
    private Producto prod;
    Movimiento movg = new Movimiento();
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
        cant = findViewById(R.id.eTCant);
        descip = findViewById(R.id.eTDescrip);


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

    private void getProds() {

        Call<String[]> prodnom = restMov.getProdnom();
        prodnom.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                if (!response.isSuccessful()) {
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

    public void getAlmas() {

        Call<String[]> almanom = restMov.getAlmanom();
        almanom.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Addmov_Activity.this, "Codigo: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] stalmas = response.body();
                ArrayAdapter lista = new ArrayAdapter(Addmov_Activity.this, android.R.layout.simple_spinner_item, stalmas);
                lista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinneralma.setAdapter(lista);

            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {

            }
        });
    }

    public void getTipo() {
        String[] stipo = {"Perdida", "Movimiento"};
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

    public void guardarMov() {

        spinnerprod.OnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sprod = adapterView.getItemAtPosition(i).toString();

                Call<Producto> productoCall = restMov.getProd(sprod);
                productoCall.enqueue(new Callback<Producto>() {
                    @Override
                    public void onResponse(Call<Producto> call, Response<Producto> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(Addmov_Activity.this, "Codigo: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        prod = response.body();
                        movg.setProducto(prod);
                    }

                    @Override
                    public void onFailure(Call<Producto> call, Throwable t) {

                    }
                });

            }
        });

        spinneralma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String salma = adapterView.getItemAtPosition(i).toString();
                Call<Almacenamiento> almacenamientoCall = restMov.getAlma(salma);
                almacenamientoCall.enqueue(new Callback<Almacenamiento>() {
                    @Override
                    public void onResponse(Call<Almacenamiento> call, Response<Almacenamiento> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(Addmov_Activity.this, "Codigo: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Almacenamiento almacenamiento = response.body();
                        movg.setAlmacenamiento(almacenamiento);

                    }

                    @Override
                    public void onFailure(Call<Almacenamiento> call, Throwable t) {

                    }
                });
            }
        });

        spinnertipo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String stipo = adapterView.getItemAtPosition(i).toString();
                String tipo = null;
                if (stipo.equals("Perdida")) {
                    tipo = "P";
                }
                if (stipo.equals("Movimiento")) {
                    tipo = "M";
                }
                movg.setMovTipo(tipo);
            }
        });

        if(date == null) {
            Toast.makeText(Addmov_Activity.this, "Debe ingresar fecha", Toast.LENGTH_SHORT).show();
            return;
        }else{
            movg.setMovFecha(date);
        }

        if (cant.getText() == null) {
            Toast.makeText(Addmov_Activity.this, "Debe ingresar cantidad", Toast.LENGTH_SHORT).show();
            return;

        }else{
            canti = Double.valueOf(cant.getText().toString());

            Call<Double> cantCall = restMov.getStock(sprod);
            cantCall.enqueue(new Callback<Double>() {
                @Override
                public void onResponse(Call<Double> call, Response<Double> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(Addmov_Activity.this, "Codigo: " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Double cant = response.body();
                    if (canti < cant) {
                        Toast.makeText(Addmov_Activity.this, "No tiene stock suficiente, tiene: " + cant.toString(), Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        movg.setMovCantidad(cant);
                        Double s = canti - cant;
                        prod.setProdStktotal(s);
                        setprod(prod);
                    }
                }

                @Override
                public void onFailure(Call<Double> call, Throwable t) {

                }
            });

        }

        if(descip.getText() == null){

            Toast.makeText(Addmov_Activity.this, "Ingrese una descripción por favor", Toast.LENGTH_SHORT).show();
            return;
        }else{
            movg.setMovDescripcion(descip.getText().toString());
        }

        Call<Movimiento> movimientoCall = restMov.crearMov(movg);
        movimientoCall.enqueue(new Callback<Movimiento>() {
            @Override
            public void onResponse(Call<Movimiento> call, Response<Movimiento> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(Addmov_Activity.this, "Código: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String ex = "Movimiento creado con exito!!";
                Toast.makeText(Addmov_Activity.this, "Movimiento fue creado con exito!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Addmov_Activity.this, Movimientos_Activity.class);
                intent.putExtra("msgCrear", ex);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Movimiento> call, Throwable t) {

            }
        });

    }

    public void setprod (Producto product){
        Call<Producto> productoCall = restMov.setProdStock(product);
        productoCall.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Addmov_Activity.this, "Código: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Addmov_Activity.this, "Producto actualizado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

            }
        });
    }
}
