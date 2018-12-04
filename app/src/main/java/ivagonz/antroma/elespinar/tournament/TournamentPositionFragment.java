package ivagonz.antroma.elespinar.tournament;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.cons_and_preferences.Constants;


public class TournamentPositionFragment extends Fragment implements View.OnClickListener {

    private Resources resources;
    private TextView infoTextView, howToTextView, busTextView, trainTextView, mapsTextView;
    private Button howToButton, busButton, trainButton, mapsButton;
    private Intent intent;
    public TournamentPositionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_tournament_position_fragment, container, false);
        resources = getResources();
        infoTextView = (TextView) v.findViewById(R.id.tournament_info);
        howToTextView = (TextView) v.findViewById(R.id.tournament_how_to_tv);
        busTextView = (TextView) v.findViewById(R.id.tournament_bus_tv);
        trainTextView = (TextView) v.findViewById(R.id.tournament_train_tv);
        mapsTextView = (TextView)v.findViewById(R.id.tournament_maps_tv);
        howToButton = (Button)v.findViewById(R.id.tournament_how_to_bt);
        busButton = (Button)v.findViewById(R.id.tournament_bus_bt);
        trainButton = (Button)v.findViewById(R.id.tournament_train_bt);
        mapsButton = (Button)v.findViewById(R.id.tournament_maps_bt);

        howToButton.setOnClickListener(this);
        busButton.setOnClickListener(this);
        trainButton.setOnClickListener(this);
        mapsButton.setOnClickListener(this);


        infoTextView.setText(resources.getString(R.string.tournament_info));
        howToTextView.setText(resources.getString(R.string.tournament_how_to));
        busTextView.setText(resources.getString(R.string.tournament_bus));
        trainTextView.setText(resources.getString(R.string.tournament_train));
        mapsTextView.setText(resources.getString(R.string.tournament_maps));

        howToButton.setText(resources.getString(R.string.tournament_how_to_bt));
        busButton.setText(resources.getString(R.string.tournament_bus_bt));
        trainButton.setText(resources.getString(R.string.tournament_train_bt));
        mapsButton.setText(resources.getString(R.string.tournament_maps_bt));
        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tournament_how_to_bt:
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, Constants.getTournamentInfo());
                break;
            case R.id.tournament_bus_bt:
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, Constants.getTournamentBus());
                break;
            case R.id.tournament_train_bt:
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, Constants.getTournamentTrain());
                break;
            case R.id.tournament_maps_bt:
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 40.739128, -4.188119);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                break;
        }
        if(intent != null) {
            startActivity(intent);
        }
    }
}
