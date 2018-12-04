package ivagonz.antroma.elespinar.tournament;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ivagonz.antroma.elespinar.R;


public class TournamentTrophiesFragment extends Fragment {

    private TextView titleTextView,subtitleTextView,textView1,textView2,textView3;
    private ImageView imageView1,imageView2,imageView3;
    private Resources resources;

    public TournamentTrophiesFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_tournament_trophies_fragment, container, false);
        resources = getResources();
        titleTextView = (TextView)v.findViewById(R.id.tournament_trophies_title);
        titleTextView.setText(resources.getString(R.string.tournament_trophies_title));
        subtitleTextView = (TextView)v.findViewById(R.id.tournament_trophies_subtitle);
        subtitleTextView.setText(resources.getString(R.string.tournament_trophies_subtitle));
        textView1 = (TextView)v.findViewById(R.id.tournament_trophies_text1);
        textView1.setText(resources.getString(R.string.tournament_trophies_text1));
        textView2 = (TextView)v.findViewById(R.id.tournament_trophies_text2);
        textView2.setText(resources.getString(R.string.tournament_trophies_text2));
        textView3 = (TextView)v.findViewById(R.id.tournament_trophies_text3);
        textView3.setText(resources.getString(R.string.tournament_trophies_text3));

        imageView1 = (ImageView)v.findViewById(R.id.tournament_trophies_image1);
        imageView1.setImageResource(R.drawable.trophies_img_1);
        imageView2 = (ImageView)v.findViewById(R.id.tournament_trophies_image2);
        imageView2.setImageResource(R.drawable.trophies_img_2);
        imageView3 = (ImageView)v.findViewById(R.id.tournament_trophies_image3);
        imageView3.setImageResource(R.drawable.trophies_img_3);
        return v;
    }
}
