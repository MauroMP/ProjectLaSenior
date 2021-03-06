package com.example.lasenioralmacenes.Adapters;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.lasenioralmacenes.AlmaActivity;
import com.example.lasenioralmacenes.Almacenamientos_Activity;

import com.example.lasenioralmacenes.Interfaces.RestUsu;

import com.example.lasenioralmacenes.Modelos.Almacenamiento;
import com.example.lasenioralmacenes.Modelos.Usuario;
import com.example.lasenioralmacenes.R;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.text.DateFormat.getDateInstance;

public class AlmacenamientosAdapter extends ArrayAdapter{

    private Context context;
    private List<Almacenamiento> listalmas;
    Usuario usu;
    String usus = Almacenamientos_Activity.nomusu;


    public AlmacenamientosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Almacenamiento> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listalmas = objects;

    }


    public View getView ( final int posicion, View convertView, ViewGroup parent   ){

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_almacenamientos, parent, false);

        callUsu(usus);

        TextView almaId = rowView.findViewById(R.id.ids);
        TextView almaNombre = rowView.findViewById(R.id.txtNombreA);
        TextView almaDescrip = rowView.findViewById(R.id.almadescrip);
        TextView almaLocale = rowView.findViewById(R.id.aLocale);
        //TextView almaCosto = rowView.findViewById(R.id.CostoOp);
        //TextView almaCapPeso = rowView.findViewById(R.id.almaCapPeso);
        // TextView almaVol = rowView.findViewById(R.id.almaVol);
        //TextView almaCantEs = rowView.findViewById(R.id.almaCantEs);


        almaId.setText(String.format("Id: %s", listalmas.get(posicion).getAlmaId()));
        almaNombre.setText(String.format("Nombre: %s", listalmas.get(posicion).getAlmaNombre()));
        almaLocale.setText(String.format("Local.: %s", listalmas.get(posicion).getLocale().getCiudade().getCiuNombre()));
        almaDescrip.setText(String.format("Descrip.: %s", listalmas.get(posicion).getAlmaDescripcion()));
        //almaCosto.setText(String.format("Costo Op.: %s", listalmas.get(posicion).getAlmaCostoop()));
       //almaCapPeso.setText(String.format("Cap. Peso.: %s", listalmas.get(posicion).getAlmaCappeso()));
       //almaVol.setText(String.format("Volúmen: %s", listalmas.get(posicion).getAlmaVolumen()));
       // almaCantEs.setText(String.format("Cant. Estiba: %s", listalmas.get(posicion).getAlmaCanestiba()));



        //Movimiento mov = new Movimiento();
        //mov.setAlmacenamiento(listalmas.get(posicion).getAlmacenamiento());


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Almacenamiento alma = new Almacenamiento();
                alma.setAlmaNombre(listalmas.get(posicion).getAlmaNombre());

                Intent intent = new Intent(context, AlmaActivity.class);
                intent.putExtra("almaNombre", alma );
                intent.putExtra("Usuario", (Serializable)usu);
                context.startActivity(intent);
                ((Almacenamientos_Activity)context).finish();

            }
        });

        return rowView;

    }
    private String getFecha(Date date) {
        String fecha;
        if (date != null) {
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
            fecha = getDateInstance().format(date);

            //fecha = dateFormat.format(date);
        } else {
            fecha = "Sin fecha";
        }
        return fecha;
    }

    private void callUsu (String nombre) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dominio.ddns.net:8086/ProyectoRest/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestUsu restUsu = retrofit.create(RestUsu.class);

        Call<Usuario> usuarioCall = restUsu.getUsuario(nombre);

        usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                usu = response.body();

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });

    }


}
