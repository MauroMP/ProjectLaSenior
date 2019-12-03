package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lasenioralmacenes.Modelos.Almacenamiento;
import com.example.lasenioralmacenes.Modelos.Usuario;

import java.io.Serializable;

public class Menu_Activity extends AppCompatActivity {


    private ImageView btProductos;
    private ImageView btMovimientos;
    private ImageView btReportes;
    private Usuario usu;
    private TextView textUsu;
    private ImageView btSalir;
    private TextView movimi;
    private TextView produs;
    String nomusu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);
    btProductos = findViewById(R.id.btProductos);
    btMovimientos = findViewById(R.id.btMovimientos);
    btReportes = findViewById(R.id.btReportes);
    textUsu = findViewById(R.id.textUsuario);
    btSalir = findViewById(R.id.btSalir);
    movimi = findViewById(R.id.Movimientos);
    produs = findViewById(R.id.productos);




    btSalir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Menu_Activity.this, MainActivity.class);
            usu = null;
            intent.putExtra("Usuario", usu);
            startActivity(intent);
            finish();
        }
    });


    Bundle bundle;
    bundle = getIntent().getExtras();
        usu = (Usuario) bundle.getSerializable("Usuario");

        if(usu != null) {

            if (usu.getPerfile().getPerfNombre().equals("Operador")) {
                desactivarBotones("Operador");
            }
            if (usu.getPerfile().getPerfNombre().equals("Supervisor")) {
                desactivarBotones("Supervisor");
            }
        }else{
            Intent intent =  new Intent(Menu_Activity.this, MainActivity.class);
            //intent.putExtra("Usuario", (Serializable)usu);
            startActivity(intent);
            finish();
        }


        btProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Menu_Activity.this, activity_Producto.class);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
                finish();
            }
        });

        btMovimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ex = "Movimientos";
                Intent intent = new Intent(Menu_Activity.this, Movimientos_Activity.class);
                intent.putExtra("mensaje", ex);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
                finish();
            }
        });

        btReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, Almacenamientos_Activity.class);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
                finish();
            }
        });

        textUsu.setText(usu.getUsuNombre() + "\n"
              + usu.getUsuApellido() + "\n" + usu.getUsuCorreo()+"\n" + usu.getPerfile().getPerfNombre());
        nomusu = usu.getUsuNombre();

    }

    private void desactivarBotones (String dato){
        if(dato.equals("Operador")){
        btProductos.setVisibility(View.INVISIBLE);
        btMovimientos.setVisibility(View.INVISIBLE);
        movimi.setVisibility(View.INVISIBLE);
        produs.setVisibility(View.INVISIBLE);
        }
        if(dato.equals("Supervisor")){
            btProductos.setVisibility(View.INVISIBLE);
            produs.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(MainActivity.this, "Por favor ingrese credenciales", Toast.LENGTH_LONG).show();
        //finish();
    }


}
