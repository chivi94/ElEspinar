package ivagonz.antroma.elespinar.pdf_swipe_refresh;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ivagonz.antroma.elespinar.cons_and_preferences.Constants;
import ivagonz.antroma.elespinar.pdf_asynctask.PDFAtpAsyncTask;
import ivagonz.antroma.elespinar.pdf_controller.PDFDialogController;

/**
 * Created by Usuario on 4/18/2017.
 */

public abstract class PDFSwipe {

    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public PDFSwipe(Context context,SwipeRefreshLayout swipeRefreshLayout, ViewPager viewPager, TabLayout tabLayout) {
        this.context = context;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;

    }



    public abstract void setSwipeRefreshLayoutSettings();

    public Context getContext() {
        return context;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }
}
