package ivagonz.antroma.elespinar.history;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ivagonz.antroma.elespinar.R;


public class HistoryValuesFragment extends Fragment {

    private TextView titleTextView, dataTextView;
    private LinearLayout linearLayout;
    private String[] titles;
    private String[] txt;
    private Resources resources;

    public HistoryValuesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_history_values_fragment, container, false);

        resources = getResources();
        titles = resources.getStringArray(R.array.history_values_titles);
        txt = resources.getStringArray(R.array.history_values_text);
        linearLayout = (LinearLayout) v.findViewById(R.id.history_values_ly);
        for (int i = 0; i < titles.length; i++) {
            titleTextView = new TextView(getContext());
            dataTextView = new TextView(getContext());
            titleTextView.setText(titles[i]);
            titleTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.textTitleColor));
            titleTextView.setTextSize(25);
            titleTextView.setTypeface(null, Typeface.BOLD_ITALIC);
            titleTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            dataTextView.setText(txt[i]);
            dataTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.textColor));
            dataTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            linearLayout.addView(titleTextView);
            linearLayout.addView(dataTextView);
        }

        return v;
    }
}
