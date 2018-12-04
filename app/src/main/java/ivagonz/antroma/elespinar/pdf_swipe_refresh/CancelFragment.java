package ivagonz.antroma.elespinar.pdf_swipe_refresh;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.pdf_asynctask.PDFItfAsyncTask;


public class CancelFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public CancelFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_cancel_fragment, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_atp);
        Bundle args = getArguments();
        int tipo = args.getInt("tipo");
        if (tipo == 1) {
            PDFAtpSwipe pdfSwipe = new PDFAtpSwipe(getContext(), swipeRefreshLayout, viewPager, tabLayout);
        } else if (tipo == 2) {
            PDFItfSwipe pdfSwipe = new PDFItfSwipe(getContext(), swipeRefreshLayout, viewPager, tabLayout);
        }

        return v;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }
}
