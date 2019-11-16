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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lasenioralmacenes.Fragments.DateFragments;
import com.example.lasenioralmacenes.Interfaces.RestMovs;
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
    private String sprod;
    private ImageView btAtras;
    private Producto prod;
    private Almacenamiento almacenamiento;
    private String tipo;
    private Double costo = 0.0;
    private String stock;
    private Double stockP;
    private String msgError;

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
        btAtras = findViewById(R.id.btAtrasA);


        getProds();
        getAlmas();
        getTipo();

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Addmov_Activity.this, Movimientos_Activity.class);
                startActivity(intent);
            }
        });

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
                if(guardarMov()) {
                    Toast.makeText(Addmov_Activity.this, "Nuevo Movimiento", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Addmov_Activity.this, "Error con algún dato al intentar crear el movimiento " + "\n" + msgError, Toast.LENGTH_SHORT).show();
                }
                }
        });

        spinnertipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stipo = adapterView.getItemAtPosition(i).toString();

                if (stipo.equals("Perdida")) {
                    tipo = "P";
                }
                if (stipo.equals("Movimiento")) {
                    tipo = "M";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerprod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sprod = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Addmov_Activity.this, "Proucto: " +sprod, Toast.LENGTH_SHORT).show();

                Call<Producto> productoCall = restMov.getProd(sprod);
                productoCall.enqueue(new Callback<Producto>() {
                    @Override
                    public void onResponse(Call<Producto> call, Response<Producto> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(Addmov_Activity.this, "Codigo: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            prod = response.body();

                        }
                    }

                    @Override
                    public void onFailure(Call<Producto> call, Throwable t) {
                        Toast.makeText(Addmov_Activity.this, "Codigo: " + t, Toast.LENGTH_SHORT).show();
                        return;

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinneralma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String salma = adapterView.getItemAtPosition(i).toString();

                Call<Almacenamiento> almacenamientoCall = restMov.getAlma(salma);
                almacenamientoCall.enqueue(new Callback<Almacenamiento>() {
                    @Override
                    public void onResponse(Call<Almacenamiento> call, Response<Almacenamiento> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(Addmov_Activity.this, "Codigo: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            almacenamiento = response.body();

                        }
                    }

                    @Override
                    public void onFailure(Call<Almacenamiento> call, Throwable t) {
                        Toast.makeText(Addmov_Activity.this, "Codigo: " + t, Toast.LENGTH_SHORT).show();
                        return;

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

    public boolean guardarMov() {
        boolean guarda = false;
        boolean guardaC = false;
        boolean guardaF = false;
        boolean guardaD = false;


        if(date == null) {
            Toast.makeText(Addmov_Activity.this, "Debe ingresar fecha", Toast.LENGTH_SHORT).show();
            msgError = "Debe ingresar fecha";

        }else{
            movg.setMovFecha(date);
            guardaF = true;
        }
        if (setCantidad()) {
            Double cante = Double.parseDouble(cant.getText().toString());
            costo = prod.getProdPrecio()* cante;
            movg.setMovCantidad(cante);
            guardaC = true;
            }
            else {
            Toast.makeText(Addmov_Activity.this, "Debe ingresar cantidad", Toast.LENGTH_SHORT).show();
            msgError = "Debe ingresar cantidad";

        }

        if(descip.getText().toString().equals("")){

            Toast.makeText(Addmov_Activity.this, "Ingrese una descripción por favor", Toast.LENGTH_SHORT).show();
            msgError = "Ingrese una descripción por favor";


        }else{
            movg.setMovDescripcion(descip.getText().toString());
            guardaD = true;
        }

        if (guardaC && guardaD && guardaF) {

            movg.setMovCosto(costo);
            movg.setProducto(prod);
            movg.setMovTipo(tipo);
            movg.setAlmacenamiento(almacenamiento);


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
                    Toast.makeText(Addmov_Activity.this, "Codigo: " + t, Toast.LENGTH_SHORT).show();
                    return;

                }
            });
            guarda = true;
        }
        return guarda;

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


    public boolean setCantidad(){
        boolean gt = false;
        Double c = null;
        Double st;
        String s = cant.getText().toString();
        if(s.equals("")){
            Toast.makeText(Addmov_Activity.this, "Debe ingresar cantidad", Toast.LENGTH_SHORT).show();

        }else {
            c = Double.parseDouble(s);
            Toast.makeText(Addmov_Activity.this, "Cantidad " + s, Toast.LENGTH_SHORT).show();
            stockP=prod.getProdStktotal();
            if(stockP!=null) {
                if (stockP < c) {
                    Toast.makeText(Addmov_Activity.this, "No tiene stock suficiente, tiene: " + stockP.toString(), Toast.LENGTH_SHORT).show();

                } else {
                    st = stockP - c;
                    Producto eprod;
                    eprod = prod;
                    eprod.setProdStktotal(st);
                    setprod(eprod);
                    gt = true;
                }
        }

        }
        return gt;
    }



    }



