package ivagonz.antroma.elespinar.tournament;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import ivagonz.antroma.elespinar.R;


public class TournamentDataFragment extends Fragment {

    private TextView titleTextView, textView, pointsTextView, prizeTextView, roundTextView;
    private Resources resources;
    private Situacion situacion;
    private String[] singleRound, doubleRound, singlePoints, doublePoints, singlePrize, doublePrize;
    private LinearLayout prizeLayout, roundLayout, pointsLayout;
    private ImageView tournamentImageView;

    public TournamentDataFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_tournament_tournament_data_fragment, container, false);
        resources = getResources();

        singleRound = resources.getStringArray(R.array.tournament_data_round);
        doubleRound = resources.getStringArray(R.array.tournament_data_round_double);
        singlePoints = resources.getStringArray(R.array.tournament_data_points);
        doublePoints = resources.getStringArray(R.array.tournament_data_points_double);
        singlePrize = resources.getStringArray(R.array.tournament_data_prize);
        doublePrize = resources.getStringArray(R.array.tournament_data_prize_double);
        prizeLayout = (LinearLayout) v.findViewById(R.id.lly_tournament_data_prize);
        roundLayout = (LinearLayout) v.findViewById(R.id.lly_tournament_data_round);
        pointsLayout = (LinearLayout) v.findViewById(R.id.lly_tournament_data_points);

        situacion = new Situacion(singleRound[0], singlePoints[0], singlePrize[0]);
        pointsTextView = new TextView(getContext());
        setTextView(pointsTextView, situacion.getPuntuacion(), pointsLayout, Typeface.SANS_SERIF, R.color.textTitleColor);
        roundTextView = new TextView(getContext());
        setTextView(roundTextView, situacion.getPosicion(), roundLayout, Typeface.SANS_SERIF, R.color.textTitleColor);
        prizeTextView = new TextView(getContext());
        setTextView(prizeTextView, situacion.getPremios(), prizeLayout, Typeface.SANS_SERIF, R.color.textTitleColor);
        for (int i = 1; i < singleRound.length; i++) {
            situacion = new Situacion(singleRound[i], singlePoints[i], singlePrize[i]);
            pointsTextView = new TextView(getContext());
            setTextView(pointsTextView, situacion.getPuntuacion(), pointsLayout, Typeface.DEFAULT, R.color.textColor);
            roundTextView = new TextView(getContext());
            setTextView(roundTextView, situacion.getPosicion(), roundLayout, Typeface.DEFAULT, R.color.textColor);
            prizeTextView = new TextView(getContext());
            setTextView(prizeTextView, situacion.getPremios(), prizeLayout, Typeface.DEFAULT, R.color.textColor);
        }
        situacion = new Situacion(doubleRound[0], doublePoints[0], doublePrize[0]);
        pointsTextView = new TextView(getContext());
        setTextView(pointsTextView, situacion.getPuntuacion(), pointsLayout, Typeface.SANS_SERIF, R.color.textTitleColor);
        roundTextView = new TextView(getContext());
        setTextView(roundTextView, situacion.getPosicion(), roundLayout, Typeface.SANS_SERIF, R.color.textTitleColor);
        prizeTextView = new TextView(getContext());
        setTextView(prizeTextView, situacion.getPremios(), prizeLayout, Typeface.SANS_SERIF, R.color.textTitleColor);
        for (int i = 1; i < doubleRound.length; i++) {
            situacion = new Situacion(doubleRound[i], doublePoints[i], doublePrize[i]);
            pointsTextView = new TextView(getContext());
            setTextView(pointsTextView, situacion.getPuntuacion(), pointsLayout, Typeface.DEFAULT, R.color.textColor);
            roundTextView = new TextView(getContext());
            setTextView(roundTextView, situacion.getPosicion(), roundLayout, Typeface.DEFAULT, R.color.textColor);
            prizeTextView = new TextView(getContext());
            setTextView(prizeTextView, situacion.getPremios(), prizeLayout, Typeface.DEFAULT, R.color.textColor);
        }
        titleTextView = (TextView) v.findViewById(R.id.tournament_data_title);
        titleTextView.setText(resources.getString(R.string.tournament_data_title));
        textView = (TextView) v.findViewById(R.id.tournament_data_text);
        textView.setText(resources.getString(R.string.tournament_data_text));
        tournamentImageView = (ImageView) v.findViewById(R.id.tournament_data_image);
        tournamentImageView.setImageResource(R.drawable.mapa);
        return v;
    }

    private void setTextView(TextView textView, String txt, LinearLayout parentLayout, Typeface typeface, int color) {
        textView.setText(txt);
        textView.setTextColor(ContextCompat.getColor(getContext(), color));
        textView.setTypeface(typeface);
        parentLayout.addView(textView);
    }
}
