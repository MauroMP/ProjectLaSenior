package com.example.lasenioralmacenes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.lasenioralmacenes.Interfaces.RestMovs;
import com.example.lasenioralmacenes.Modelos.Movimiento;
import com.example.lasenioralmacenes.MovActivity;
import com.example.lasenioralmacenes.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovimientosAdapter extends ArrayAdapter {

    private Context context;
    private List<Movimiento> listmovs;

    public MovimientosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Movimiento> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listmovs = objects;

    }

    public View getView ( final int posicion, View convertView, ViewGroup parent   ){

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_movimientos, parent, false);

        TextView movId = rowView.findViewById(R.id.ids);
        TextView movFecha = rowView.findViewById(R.id.fecha);
        TextView movCosto = rowView.findViewById(R.id.Costo);
        TextView movCantidad = rowView.findViewById(R.id.cantidad);
        TextView movProducto = rowView.findViewById(R.id.Producto);
        TextView movAlmacen = rowView.findViewById(R.id.Almacen);
        TextView movTipo = rowView.findViewById(R.id.tipo);
        final Date date = listmovs.get(posicion).getMovFecha();



        movId.setText(String.format("Id: %s", listmovs.get(posicion).getMovId()));
        movFecha.setText(String.format("%s", getFecha(date)));
        movCosto.setText(String.format("%s", listmovs.get(posicion).getMovCosto()));
        movCantidad.setText(String.format("%s", listmovs.get(posicion).getMovCantidad()));
        movProducto.setText(String.format("%s", listmovs.get(posicion).getProducto().getProdNombre()));
        movAlmacen.setText(String.format("%s", listmovs.get(posicion).getAlmacenamiento().getAlmaNombre()));
        movTipo.setText(String.format("%s", listmovs.get(posicion).getMovTipo()));

        Movimiento mov = new Movimiento();
        mov.setAlmacenamiento(listmovs.get(posicion).getAlmacenamiento());


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Movimiento mov = new Movimiento();
                mov.setMovId(listmovs.get(posicion).getMovId());

                Intent intent = new Intent(context, MovActivity.class);
                intent.putExtra("movid", mov );
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
