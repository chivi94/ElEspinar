package ivagonz.antroma.elespinar.pdf_asynctask;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.io.File;
import java.util.ArrayList;

import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.activities.NavigationDrawerActivity;
import ivagonz.antroma.elespinar.cons_and_preferences.Constants;
import ivagonz.antroma.elespinar.fem_itf.ITFFragment;
import ivagonz.antroma.elespinar.pageradapter.ViewPagerAdapter;
import ivagonz.antroma.elespinar.pdf_swipe_refresh.CancelFragment;

/**
 * Created by ivgor on 21/03/2017.
 */

public class PDFItfAsyncTask extends PDFAsyncTask {




    private ViewPager viewPager;
    private ProgressDialog progressDialog;
    private TabLayout tabLayout;

    public PDFItfAsyncTask(Context context, ViewPager viewpager, TabLayout tabLayout) {
        super(context);
        this.viewPager = viewpager;
        this.tabLayout = tabLayout;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getContext().getResources().getString(R.string.dialog_download));
        progressDialog.show();

    }


    @Override
    protected void onPostExecute(ArrayList<File> files) {
        super.onPostExecute(files);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        progressDialog.dismiss();

    }


    @Override
    public void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(((NavigationDrawerActivity)getContext()).getSupportFragmentManager());

        Bundle args = null;
        ITFFragment itfFragment = null;
        String [] names = new String[]{getContext().getResources().getString(R.string.atp_challenger_individual),getContext().getResources().getString(R.string.atp_challenger_doubles),
                getContext().getResources().getString(R.string.atp_challenger_previous),getContext().getResources().getString(R.string.atp_challenger_order)};
        for (int i = 4; i < 8; i++) {
            args = new Bundle();
            args.putSerializable("file", Constants.getFiles().get(i));
            itfFragment = new ITFFragment();
            itfFragment.setArguments(args);
            adapter.addFrag(itfFragment, names[i-4]);
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setUpCancelViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(((NavigationDrawerActivity) getContext()).getSupportFragmentManager());


        CancelFragment cancelFragment = null;
        String [] names = new String[]{getContext().getResources().getString(R.string.atp_challenger_individual),getContext().getResources().getString(R.string.atp_challenger_doubles),
                getContext().getResources().getString(R.string.atp_challenger_previous),getContext().getResources().getString(R.string.atp_challenger_order)};
        Bundle args = null;
        for (int i = 0; i < names.length; i++) {
            args = new Bundle();
            args.putInt("tipo",2);
            cancelFragment = new CancelFragment();
            cancelFragment.setViewPager(viewPager);
            cancelFragment.setTabLayout(tabLayout);
            cancelFragment.setArguments(args);
            adapter.addFrag(cancelFragment, names[i]);
        }
        viewPager.setAdapter(adapter);

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        setUpCancelViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }
}
