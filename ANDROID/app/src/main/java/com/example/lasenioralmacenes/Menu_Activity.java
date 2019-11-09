package com.example.lasenioralmacenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lasenioralmacenes.Modelos.Usuario;

import java.io.Serializable;

public class Menu_Activity extends AppCompatActivity {

    Button btProductos;
    Button btMovimientos;
    Button btReportes;
    Usuario usu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);
    btProductos = findViewById(R.id.btProductos);
    btMovimientos = findViewById(R.id.btMovimientos);
    btReportes = findViewById(R.id.btAlmacenes);

    Bundle bundle = new Bundle();
    bundle = getIntent().getExtras();
        usu = (Usuario) bundle.getSerializable("Usuario");

        if(usu.getPerfile().equals("Operador")){
            desactivarBotones("Operador");
        }
        if(usu.getPerfile().equals("Supervisor")){
            desactivarBotones("Supervisor");
        }

        btProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent();
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
