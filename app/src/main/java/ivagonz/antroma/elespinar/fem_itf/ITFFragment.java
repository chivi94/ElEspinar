package ivagonz.antroma.elespinar.fem_itf;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.pdf_asynctask.PDFPagerAdapter;


public class ITFFragment extends Fragment {

    private VerticalViewPager verticalViewPager;
    private PDFPagerAdapter pdfPagerAdapter;

    public ITFFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_tf_fragment, container, false);
        verticalViewPager = (VerticalViewPager) v.findViewById(R.id.itf_single_vertical_vp);

        Bundle args = getArguments();
        File file = (File) args.getSerializable("file");
        if (file != null) {
            pdfPagerAdapter = new PDFPagerAdapter(getContext(), file);
            verticalViewPager.setAdapter(pdfPagerAdapter);
        }
        return v;
    }
}
