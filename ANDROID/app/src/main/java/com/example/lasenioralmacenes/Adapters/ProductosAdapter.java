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

import com.example.lasenioralmacenes.Interfaces.RestUsu;
import com.example.lasenioralmacenes.Modelos.Producto;
import com.example.lasenioralmacenes.Modelos.Usuario;
import com.example.lasenioralmacenes.PrdActivity;
import com.example.lasenioralmacenes.R;
import com.example.lasenioralmacenes.activity_Producto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.text.DateFormat.getDateInstance;

public class ProductosAdapter extends ArrayAdapter {

    private Context context;
    private List<Producto> listprod;
    Usuario usu;
    String usus = activity_Producto.nomusup;

    public ProductosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listprod = objects;

    }

    public View getView (final int posicion, View convertView, ViewGroup parent   ){
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_productos, parent, false);

        callUsu(usus);

        TextView prodid = rowView.findViewById(R.id.idp);
        TextView prodnom = rowView.findViewById(R.id.nombre);
        TextView prodpre = rowView.findViewById(R.id.precio);
        TextView prodpeso = rowView.findViewById(R.id.peso);
        TextView prodFVen = rowView.findViewById(R.id.FVenc);
        TextView prodAlmacen = rowView.findViewById(R.id.Alma);
        TextView prodFami = rowView.findViewById(R.id.Famil);
        TextView prodStkT = rowView.findViewById(R.id.StockT);
        final Date date = listprod.get(posicion).getProdFven();

        prodid.setText(String.format("Id: %s", listprod.get(posicion).getProdId()));
        prodnom.setText(String.format("%s", listprod.get(posicion).getProdNombre()));
        prodpre.setText(String.format("%s", listprod.get(posicion).getProdPrecio()));
        prodpeso.setText(String.format("%s",listprod.get(posicion).getProdPeso()));
        prodFVen.setText(String.format("%s",getFecha(date)));
        prodAlmacen.setText(String.format("%s",listprod.get(posicion).getAlmacenamiento().getAlmaNombre()));
        prodFami.setText(String.format("%s",listprod.get(posicion).getFamilia().getFamiNombre()));
        prodStkT.setText(String.format("%s",listprod.get(posicion).getProdStktotal()));

        Producto prod = new Producto();
        prod.setAlmacenamiento(listprod.get(posicion).getAlmacenamiento());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Producto prd = new Producto();
                prd.setProdNombre(listprod.get(posicion).getProdNombre());

                Intent intent = new Intent(context, PrdActivity.class);
                intent.putExtra("Usuario", usu);
                intent.putExtra("prod_nom", prd );
                context.startActivity(intent);
                ((activity_Producto)context).finish();

            }
        });

        return rowView;

    }

    private String getFecha(Date date) {
        String fecha;
        if (date != null) {
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
            //fecha = dateFormat.format(date);
            fecha = getDateInstance().format(date);
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
