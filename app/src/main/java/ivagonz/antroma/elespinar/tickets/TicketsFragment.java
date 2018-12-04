package ivagonz.antroma.elespinar.tickets;



import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import ivagonz.antroma.elespinar.R;


public class TicketsFragment extends Fragment {



    private Button emailButton;
    private String[] infoStrings;
    private String[] priceStrings;
    private Entrada entrada;
    private Resources resources;
    private ArrayList<Entrada>entradas;
    private ListView listView;
    private TicketsAdapter adapter;

    public TicketsFragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Sección para parte lógica
        View v = inflater.inflate(R.layout.ly_tickets_fragment, container, false);
        listView = (ListView)v.findViewById(R.id.tickets_lv) ;
        entradas = new ArrayList<>();
        resources = getActivity().getResources();
        infoStrings = resources.getStringArray(R.array.tickets_info);
        priceStrings = resources.getStringArray(R.array.tickets_price);
        for (int i = 0 ; i <infoStrings.length;i++){
            entrada = new Entrada(infoStrings[i],priceStrings[i]);
            entradas.add(entrada);
        }
        adapter = new TicketsAdapter(getActivity().getApplicationContext(),entradas);
        listView.setAdapter(adapter);

        emailButton = (Button)v.findViewById(R.id.tickets_mail_button);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{resources.getString(R.string.tickets_address)});
                intent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.tickets_extra));
                startActivity(Intent
                        .createChooser(intent, getString(R.string.select_app)));
            }
        });

        return v;
    }

}
