package ivagonz.antroma.elespinar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.pageradapter.ViewPagerAdapter;
import ivagonz.antroma.elespinar.tournament.TournamentContactFragment;
import ivagonz.antroma.elespinar.tournament.TournamentDataFragment;
import ivagonz.antroma.elespinar.tournament.TournamentPositionFragment;
import ivagonz.antroma.elespinar.tournament.TournamentTrophiesFragment;

public class TournamentActivity extends NavigationDrawerActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Intent intent;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_tournament_activity);
        drawerLayout = (DrawerLayout) findViewById(R.id.tournament_activity_drawer_ly);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar, getResources().getString(R.string.tournament_name));
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(
                actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.base_naw_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView, drawerLayout);
        }
        intent = getIntent();
        position = intent.getIntExtra("position",0);
        navigationView.getMenu().getItem(position).setChecked(true);

        viewPager = (ViewPager) findViewById(R.id.tournament_activity_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tournament_activity_tabs);
        tabLayout.setupWithViewPager(viewPager);
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




    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TournamentPositionFragment(), getResources().getString(R.string.tournament_position));
        adapter.addFrag(new TournamentDataFragment(), getResources().getString(R.string.tournament_tournament_data));
        adapter.addFrag(new TournamentTrophiesFragment(), getResources().getString(R.string.tournament_trophies));
        adapter.addFrag(new TournamentContactFragment(), getResources().getString(R.string.tournament_contact));
        viewPager.setAdapter(adapter);
    }



}
