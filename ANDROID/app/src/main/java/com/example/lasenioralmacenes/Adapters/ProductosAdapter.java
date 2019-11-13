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

import com.example.lasenioralmacenes.Modelos.Producto;
import com.example.lasenioralmacenes.PrdActivity;
import com.example.lasenioralmacenes.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductosAdapter extends ArrayAdapter {

    private Context context;
    private List<Producto> listprod;

    public ProductosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listprod = objects;

    }

    public View getView (final int posicion, View convertView, ViewGroup parent   ){
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_productos, parent, false);

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
                prd.setProdId(listprod.get(posicion).getProdId());

                Intent intent = new Intent(context, PrdActivity.class);
                intent.putExtra("movprd", prd );
                context.startActivity(intent);

            }
        });

        return rowView;

    }

    private String getFecha(Date date) {
        String fecha;
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
            fecha = dateFormat.format(date);
        } else {
            fecha = "Sin fecha";
        }
        return fecha;
    }

}
