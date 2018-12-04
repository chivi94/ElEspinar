package ivagonz.antroma.elespinar.tickets;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.ArrayList;

import ivagonz.antroma.elespinar.R;

/**
 * Created by Ivan on 09/02/2017.
 */

public class TicketsAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Entrada> entradas;

    public TicketsAdapter(Context context, ArrayList entradas) {
        super(context, R.layout.ly_tickets_listview, entradas);
        this.context = context;
        this.entradas= entradas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.ly_tickets_listview,null);

        TextView info = (TextView) item.findViewById(R.id.tickets_tv_info);
        info.setText(entradas.get(position).getInformacion());


        TextView precio = (TextView) item.findViewById(R.id.tickets_tv_price);
        precio.setText(entradas.get(position).getPrecio());

        return item;
    }
}
