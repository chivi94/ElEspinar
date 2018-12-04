package ivagonz.antroma.elespinar.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.atp_challenger.ATPChallengerFragment;
import ivagonz.antroma.elespinar.cons_and_preferences.Constants;
import ivagonz.antroma.elespinar.pageradapter.ViewPagerAdapter;
import ivagonz.antroma.elespinar.pdf_asynctask.PDFAtpAsyncTask;
import ivagonz.antroma.elespinar.pdf_controller.PDFDialogController;

public class ATPChallengerActivity extends NavigationDrawerActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Intent intent;
    private int position;
    private static ArrayList<File> files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_atp_challenger_activity);
        drawerLayout = (DrawerLayout) findViewById(R.id.atp_challenger_activity_drawer_ly);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.atp_challenger_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.atp_challenger_tabs);
        setToolbar(toolbar, getResources().getString(R.string.atp_name));
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(
                actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.base_naw_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView, drawerLayout);
        }
        intent = getIntent();
        position = intent.getIntExtra("position", 0);
        navigationView.getMenu().getItem(position).setChecked(true);
        files = Constants.getFiles();
        if (!files.isEmpty()) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        } else if (files.isEmpty() && networkStatus()) {
            PDFAtpAsyncTask pdfAsyncTask = new PDFAtpAsyncTask(this, viewPager, tabLayout);
            PDFDialogController pdfDialogController = new PDFDialogController(pdfAsyncTask, this, new String[]{Constants.getAtpSingleUrl(), Constants.getAtpDoubleUrl(), Constants.getAtpPreviousUrl(), Constants.getAtpGameOrderUrl(),
                    Constants.getItfSingleUrl(), Constants.getItfDoubleUrl(), Constants.getItfPreviousUrl(), Constants.getItfGameOrderUrl()}, pdfAsyncTask.getProgressDialog());
            pdfDialogController.start();
        } else {
            showNetworkDialog();
        }


    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
        intent.putExtra("position", 0);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            finish();
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    private void showNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                ATPChallengerActivity.this);
        builder.setView(getLayoutInflater().inflate(R.layout.ly_network_dialog, null));
        builder.setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.cancel();
                onBackPressed();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.cancel();
                    onBackPressed();
                }
                return true;
            }
        });
        dialog.show();
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle args = null;
        ATPChallengerFragment atpChallengerFragment = null;
        String[] names = new String[]{getResources().getString(R.string.atp_challenger_individual), getResources().getString(R.string.atp_challenger_doubles),
                getResources().getString(R.string.atp_challenger_previous), getResources().getString(R.string.atp_challenger_order)};
        for (int i = 0; i < 4; i++) {
            args = new Bundle();
            args.putSerializable("file", files.get(i));
            atpChallengerFragment = new ATPChallengerFragment();
            atpChallengerFragment.setArguments(args);
            adapter.addFrag(atpChallengerFragment, names[i]);
        }
        viewPager.setAdapter(adapter);

    }


}
