package ivagonz.antroma.elespinar.atp_challenger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.pdf_asynctask.PDFPagerAdapter;


public class ATPChallengerFragment extends Fragment {

    private VerticalViewPager verticalViewPager;
    private PDFPagerAdapter pdfPagerAdapter;

    public ATPChallengerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ly_atp_challenger_fragment, container, false);
        verticalViewPager = (VerticalViewPager) v.findViewById(R.id.double_vertical_vp);

        Bundle args = getArguments();
        File file = (File) args.getSerializable("file");
        if (file != null) {
            pdfPagerAdapter = new PDFPagerAdapter(getContext(), file);
            verticalViewPager.setAdapter(pdfPagerAdapter);
        }
        return v;

    }
}
