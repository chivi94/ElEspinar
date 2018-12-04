package ivagonz.antroma.elespinar.history;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ivagonz.antroma.elespinar.R;


public class HistoryAwardsFragment extends Fragment {

    private TextView dataTextView;
    private TextView titleTextView;
    private Resources resources;

    public HistoryAwardsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_history_awards_fragment, container, false);
        resources = getResources();

        titleTextView = (TextView)v.findViewById(R.id.history_awards_title);
        titleTextView.setText(resources.getString(R.string.history_awards_title));
        dataTextView = (TextView)v.findViewById(R.id.history_awards_text);
        dataTextView.setText(resources.getString(R.string.history_awards_text));
        return v;
    }
}
