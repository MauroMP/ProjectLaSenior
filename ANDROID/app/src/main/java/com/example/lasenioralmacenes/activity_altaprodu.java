package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.icu.util.Calendar;
import com.example.lasenioralmacenes.Fragments.DateFragments;
import com.example.lasenioralmacenes.Interfaces.RestMovs;
import com.example.lasenioralmacenes.Interfaces.RestProd;

import com.example.lasenioralmacenes.Modelos.Almacenamiento;
import com.example.lasenioralmacenes.Modelos.Familia;
import com.example.lasenioralmacenes.Modelos.Producto;
import com.example.lasenioralmacenes.Modelos.Usuario;

import java.text.DateFormat;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_altaprodu extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner spinneralma;
    private Spinner spinnerfami;
    private Spinner spinnerestiba;
    private Spinner spinnerfragmentacion;
    private TextView fecela;
    private TextView fecvenc;
    private EditText nomprod;
    private EditText stkmin;
    private EditText stktotal;
    private EditText peso;
    private EditText volProd;
    private EditText loteProd;
    private EditText preProd;
    private ImageView btnGuar;
    private ImageView btnVolv;
    private ImageView btFecE;
    private ImageView btFecV;
    private TextView fecElab;
    private TextView fecVenc;
    private Almacenamiento almacenamiento;
    private Familia familia;
    private String msgError;
    private double pesopro;
    private Long stkminpro;
    private Double stktotalpro;
    private String nombre;
    private String lote;
    private Double precio;
    private Double volumen;
    private Date date;
    private String esti;
    private String segme;
    private Date dateE;
    private Date dateV;
    private boolean fecE = false;
    private boolean fecV = false;
    Usuario usu;

    Producto prog = new Producto();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RestProd restPro = retrofit.create(RestProd.class);
    RestMovs restMov = retrofit.create(RestMovs.class);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altaprodu);

        spinneralma = findViewById(R.id.spAlma);
        spinnerfami = findViewById(R.id.spFami);
        spinnerestiba = findViewById(R.id.spEstiba);
        spinnerfragmentacion = findViewById(R.id.spSegmen);
        btFecE = findViewById(R.id.btCalendar);
        btFecV = findViewById(R.id.btCalendar2);
        nomprod = findViewById(R.id.eTNombre);
        stkmin = findViewById(R.id.eTStkMin);
        stktotal = findViewById(R.id.eTStkTotal);
        peso = findViewById(R.id.eTPeso);
        volProd = findViewById(R.id.eTVolumen);
        loteProd = findViewById(R.id.eTLote);
        preProd = findViewById(R.id.eTPrecio);
        fecela = findViewById(R.id.etFechaElab);
        fecvenc = findViewById(R.id.etFechaVenc);
        btnGuar = findViewById(R.id.GuardarPro);
        btnVolv = findViewById(R.id.btAtrasPro);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        usu = (Usuario)bundle.getSerializable("Usuario");


        getAlmas();
        getFami();
        getEstiba();
        getSegmentacion();

        btnVolv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_altaprodu.this, activity_Producto.class);
                intent.putExtra("Usuario", usu);
                startActivity(intent);
                finish();
            }
        });

        btFecE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DateFragments();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        btFecV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DateFragments();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        btnGuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(guardarProd()) {
                    String ex = "Producto creado con exito!!";
                    Toast.makeText(activity_altaprodu.this, ex, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_altaprodu.this, activity_Producto.class);
                    intent.putExtra("Usuario", usu);
                    intent.putExtra("msgCrear", ex);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(activity_altaprodu.this, "No se pudo guardar el Producto, verifique los datos " + "\n" + msgError, Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        spinnerestiba.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sesti = adapterView.getItemAtPosition(i).toString();

                if (sesti.equals("SI")) {
                    esti = "S";
                }
                if (sesti.equals("NO")) {
                    esti = "N";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerfragmentacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String ssegme = adapterView.getItemAtPosition(i).toString();

                if (ssegme.equals("SI")) {
                    segme = "S";
                }
                if (ssegme.equals("NO")) {
                    segme = "N";
                }

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
                            Toast.makeText(activity_altaprodu.this, "Codigo: 1" + response.code() , Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            almacenamiento = response.body();

                        }
                    }

                    @Override
                    public void onFailure(Call<Almacenamiento> call, Throwable t) {
                        Toast.makeText(activity_altaprodu.this, "Codigo: 2" + t, Toast.LENGTH_SHORT).show();
                        return;

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerfami.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String sfami = adapterView.getItemAtPosition(i).toString();
                Call<Familia> familiacall = restPro.getFami(sfami);
                familiacall.enqueue(new Callback<Familia>() {
                    @Override
                    public void onResponse(Call<Familia> call, Response<Familia> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(activity_altaprodu.this, "Codigo: 3" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            familia = response.body();

                        }
                    }

                    @Override
                    public void onFailure(Call<Familia> call, Throwable t) {
                        Toast.makeText(activity_altaprodu.this, "Codigo: 4" + t, Toast.LENGTH_SHORT).show();
                        return;

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btFecE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecE = true;
                DialogFragment datePicker = new DateFragments();
                datePicker.show(getSupportFragmentManager(), "date picker");


            }
        });

        btFecV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecV = true;
                DialogFragment datePicker = new DateFragments();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });


    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        if(fecE){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
            date = c.getTime();
            dateE = date;
            fecela.setText(currentDateString);
            fecE = false;
            prog.setProdFelab(dateE);
        }
        if(fecV){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
            date = c.getTime();
            dateV = date;
            fecvenc.setText(currentDateString);
            fecV =false;
            prog.setProdFven(dateV);
        }
    }

    private boolean guardarProd() {
        boolean guarda = false;
        boolean guardaC = false;
        boolean guardaF = false;
        boolean guardaD = false;
        boolean guardaA = false;

        if(date == null) {
            Toast.makeText(activity_altaprodu.this, "Debe ingresar fecha", Toast.LENGTH_SHORT).show();
            msgError = "Debe ingresar fecha";

        }else{
            prog.setProdFelab(date);
            guardaF = true;
        }

        if(date == null) {
            Toast.makeText(activity_altaprodu.this, "Debe ingresar fecha", Toast.LENGTH_SHORT).show();
            msgError = "Debe ingresar fecha";

        }else{
            prog.setProdFven(date);
            guardaF = true;
        }

        /// Chequeo que la fecha de vencimiento sea mayor a la elaboracion.
        if (dateV.after(dateE)){
            Toast.makeText(activity_altaprodu.this, "Entre al Guardar", Toast.LENGTH_LONG).show();
            guardaA = true;
        }else{
            msgError = "La fecha de Vencimiento debe ser mayor a la Elaboración";
        }

        // se verifica los datos no esten vacios
  //      guardaD = validardatos();

        /// Chequeo que el Stock Total sea mayor que el stock minimo
        double s = Long.parseLong(stkmin.getText().toString());
        double tot = Double.parseDouble(stktotal.getText().toString());
        if (tot > s ){
            guardaC = true;
        } else{
            msgError = "El stock Mínimo no puede ser mayor al stock Total";
        }


        if (guardaF && guardaA && guardaC) {
            prog.setProdNombre(nomprod.getText().toString());
            prog.setProdLote(loteProd.getText().toString());
            prog.setProdPeso(Double.parseDouble(peso.getText().toString()));
            prog.setProdPrecio(Double.parseDouble(preProd.getText().toString()));
            prog.setProdStkmin(Long.parseLong(stkmin.getText().toString()));
            prog.setProdStktotal(Double.parseDouble(stktotal.getText().toString()));
            prog.setProdVol(Double.parseDouble(volProd.getText().toString()));
            prog.setAlmacenamiento(almacenamiento);
            prog.setFamilia(familia);
            prog.setProdEstiba(esti);
            prog.setProdSegmetac(segme);

            Call<Producto> productoCall = restPro.crearPro(prog);
            productoCall.enqueue(new Callback<Producto>() {
                @Override
                public void onResponse(Call<Producto> call, Response<Producto> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(activity_altaprodu.this, "Código: 5" + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(activity_altaprodu.this, "Producto fue creado con exito!!", Toast.LENGTH_LONG).show();


                }

                @Override
                public void onFailure(Call<Producto> call, Throwable t) {
                    Toast.makeText(activity_altaprodu.this, "Codigo: 6" + t, Toast.LENGTH_SHORT).show();
                    return;

                }
            });
            guarda = true;
        }
        return guarda;

    }
/*
    private boolean validardatos() {
        double min = Long.parseLong(stkmin.getText().toString());
        double tot = Double.parseDouble(stktotal.getText().toString());

        boolean guardaD = true;
        if (nombre.equals("")){
            guardaD = false;
            msgError = "El nombre no puede quedar Vacio!!";
        }else if(min == 0 || tot == 0){
            guardaD = false;
            msgError = "Los stock no pueden quedar Vacios!!";
        } else if(volumen == 0  || precio == 0){
            guardaD = false;
            msgError = "Verificar que volumen o precio no esten Vacio!!";
        } else if (lote.equals("")){
            guardaD = false;
            msgError = "El lote no puede estar Vacio!!";
        } else if (pesopro == 0){
            guardaD = false;
            msgError = "El peso del producto no puede estar vacio!!!";
        }
        return guardaD;
    }
*/
    public void getEstiba() {
        String[] stipo = {"NO", "SI"};
        ArrayAdapter lista = new ArrayAdapter(activity_altaprodu.this, android.R.layout.simple_spinner_item, stipo);
        lista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerestiba.setAdapter(lista);

    }

    public void getSegmentacion() {
        String[] stipo = {"NO", "SI"};
        ArrayAdapter lista = new ArrayAdapter(activity_altaprodu.this, android.R.layout.simple_spinner_item, stipo);
        lista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfragmentacion.setAdapter(lista);

    }

    private void getFami() {
        Call<String[]> faminom = restPro.getFaminom();
        faminom.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(activity_altaprodu.this, "Codigo: 7 " + response.code(),Toast.LENGTH_SHORT);
                    return;
                }
                String[] stfami = response.body();
                ArrayAdapter listfam = new ArrayAdapter(activity_altaprodu.this, android.R.layout.simple_spinner_item, stfami);
                listfam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerfami.setAdapter(listfam);

            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {

            }
        });
    }

    private void getAlmas() {
        Call<String[]> almanom = restPro.getAlmanom();
        almanom.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(activity_altaprodu.this, "Codigo: 8 " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] stalmas = response.body();
                ArrayAdapter lista = new ArrayAdapter(activity_altaprodu.this, android.R.layout.simple_spinner_item, stalmas);
                lista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinneralma.setAdapter(lista);

            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {

            }
        });
    }

}

