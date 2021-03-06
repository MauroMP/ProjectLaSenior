package com.example.lasenioralmacenes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;


import com.example.lasenioralmacenes.Interfaces.RestUsu;
import com.example.lasenioralmacenes.Modelos.Movimiento;
import com.example.lasenioralmacenes.Modelos.Usuario;
import com.example.lasenioralmacenes.MovActivity;
import com.example.lasenioralmacenes.Movimientos_Activity;
import com.example.lasenioralmacenes.R;


import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.text.DateFormat.getDateInstance;

public class MovimientosAdapter extends ArrayAdapter {

    private Context context;
    private List<Movimiento> listmovs;
    Usuario usu;
    String usus = Movimientos_Activity.nomusum;

    public MovimientosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Movimiento> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listmovs = objects;

    }

    public View getView ( final int posicion, View convertView, ViewGroup parent   ){

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_movimientos, parent, false);

        callUsu(usus);

        TextView movId = rowView.findViewById(R.id.ids);
        TextView movFecha = rowView.findViewById(R.id.txtNombreA);
        TextView movCosto = rowView.findViewById(R.id.CostoOp);
        TextView movCantidad = rowView.findViewById(R.id.almaCapPeso);
        TextView movProducto = rowView.findViewById(R.id.aLocale);
        TextView movAlmacen = rowView.findViewById(R.id.almaVol);
        TextView movTipo = rowView.findViewById(R.id.almaCantEs);
        TextView movDescrip = rowView.findViewById(R.id.movdescrip);
        final Date date = listmovs.get(posicion).getMovFecha();


        movId.setText(String.format("Id: %s", listmovs.get(posicion).getMovId()));
        movFecha.setText(String.format("%s", getFecha(date)));
        movCosto.setText(String.format("Costo: %s", listmovs.get(posicion).getMovCosto()));
        movCantidad.setText(String.format("Cant.: %s", listmovs.get(posicion).getMovCantidad()));
        movProducto.setText(String.format("Prod.: %s", listmovs.get(posicion).getProducto().getProdNombre()));
        movAlmacen.setText(String.format("Almacen: %s", listmovs.get(posicion).getAlmacenamiento().getAlmaNombre()));
        movTipo.setText(String.format("Tipo: %s", listmovs.get(posicion).getMovTipo()));
        movDescrip.setText(String.format("Descrip.: %s", listmovs.get(posicion).getMovDescripcion() ));


        Movimiento mov = new Movimiento();
        mov.setAlmacenamiento(listmovs.get(posicion).getAlmacenamiento());


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Movimiento mov = new Movimiento();
                mov.setMovId(listmovs.get(posicion).getMovId());

                Intent intent = new Intent(context, MovActivity.class);
                intent.putExtra("Usuario", usu);
                intent.putExtra("movid", mov );
                context.startActivity(intent);
                ((Movimientos_Activity)context).finish();

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
