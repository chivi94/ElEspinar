package ivagonz.antroma.elespinar.pdf_swipe_refresh;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

import ivagonz.antroma.elespinar.cons_and_preferences.Constants;
import ivagonz.antroma.elespinar.pdf_asynctask.PDFAtpAsyncTask;
import ivagonz.antroma.elespinar.pdf_asynctask.PDFItfAsyncTask;
import ivagonz.antroma.elespinar.pdf_controller.PDFDialogController;

/**
 * Created by Ivan on 18/04/2017.
 */

public class PDFItfSwipe extends PDFSwipe {

    public PDFItfSwipe(Context context, SwipeRefreshLayout swipeRefreshLayout, ViewPager viewPager, TabLayout tabLayout) {
        super(context, swipeRefreshLayout, viewPager, tabLayout);
        setSwipeRefreshLayoutSettings();
    }

    @Override
    public void setSwipeRefreshLayoutSettings() {
        getSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                PDFItfAsyncTask pdfAsyncTask = new PDFItfAsyncTask(getContext(), getViewPager(), getTabLayout());
                PDFDialogController pdfDialogController = new PDFDialogController(pdfAsyncTask, getContext(), new String[]{Constants.getAtpSingleUrl(), Constants.getAtpDoubleUrl(), Constants.getAtpPreviousUrl(), Constants.getAtpGameOrderUrl(),
                        Constants.getItfSingleUrl(), Constants.getItfDoubleUrl(), Constants.getItfPreviousUrl(), Constants.getItfGameOrderUrl()}, pdfAsyncTask.getProgressDialog());
                pdfDialogController.start();
            }
        });
        getSwipeRefreshLayout().setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
}
