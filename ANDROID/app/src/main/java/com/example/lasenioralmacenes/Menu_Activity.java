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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);
    btProductos = findViewById(R.id.btProductos);
    btMovimientos = findViewById(R.id.btMovimientos);
    btReportes = findViewById(R.id.btReportes);
    textUsu = findViewById(R.id.textUsuario);
    btSalir = findViewById(R.id.btSalir);



    btSalir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Menu_Activity.this, MainActivity.class);
            startActivity(intent);
        }
    });


    Bundle bundle;
    bundle = getIntent().getExtras();
        usu = (Usuario) bundle.getSerializable("Usuario");

        if(usu.getPerfile().getPerfNombre().equals("Operador")){
            desactivarBotones("Operador");
        }
        if(usu.getPerfile().getPerfNombre().equals("Supervisor")){
            desactivarBotones("Supervisor");
        }

        btProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Menu_Activity.this, activity_Producto.class);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
            }
        });

        btMovimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Activity.this, Movimientos_Activity.class);
                intent.putExtra("Usuario", (Serializable)usu);
                startActivity(intent);
            }
        });

        btReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, Almacenamientos_Activity.class);
                startActivity(intent);
            }
        });

        textUsu.setText(usu.getUsuNombre() + "\n"
              + usu.getUsuApellido() + "\n" + usu.getUsuCorreo()+"\n" + usu.getPerfile().getPerfNombre());

    }

    private void desactivarBotones (String dato){
        if(dato.equals("Operador")){
        btProductos.setVisibility(View.INVISIBLE);
        btMovimientos.setVisibility(View.INVISIBLE);
        }
        if(dato.equals("Supervisor")){
            btProductos.setVisibility(View.INVISIBLE);
        }
    }
}
