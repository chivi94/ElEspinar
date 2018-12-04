package ivagonz.antroma.elespinar.history;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ivagonz.antroma.elespinar.R;


public class HistoryStatsFragment extends Fragment {

    private TextView dataTitleTextView, dataTextView;
    private Resources resources;

    public HistoryStatsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_history_stats_fragment, container, false);
        resources = getResources();

        dataTitleTextView = (TextView)v.findViewById(R.id.history_stats_data);
        dataTitleTextView.setText(resources.getString(R.string.history_data_title));

        dataTextView = (TextView)v.findViewById(R.id.history_stats_data_text);
        dataTextView.setText(resources.getString(R.string.history_data_text));




        return v;
    }
}
