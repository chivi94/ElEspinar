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
import ivagonz.antroma.elespinar.history.HistoryAwardsFragment;
import ivagonz.antroma.elespinar.history.HistoryCommitteeFragment;
import ivagonz.antroma.elespinar.history.HistoryDoubleFragment;
import ivagonz.antroma.elespinar.history.HistoryPlayersFragment;
import ivagonz.antroma.elespinar.history.HistorySingleFragment;
import ivagonz.antroma.elespinar.history.HistoryStatsFragment;
import ivagonz.antroma.elespinar.history.HistoryValuesFragment;
import ivagonz.antroma.elespinar.pageradapter.ViewPagerAdapter;

public class HistoryActivity extends NavigationDrawerActivity {


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
        setContentView(R.layout.ly_history_activity);
        drawerLayout = (DrawerLayout) findViewById(R.id.history_activity_drawer_ly);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar, getResources().getString(R.string.history_name));
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

        viewPager = (ViewPager) findViewById(R.id.history_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.history_tabs);
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
        adapter.addFrag(new HistoryStatsFragment(), getResources().getString(R.string.history_stats));
        adapter.addFrag(new HistoryPlayersFragment(), getResources().getString(R.string.history_players));
        adapter.addFrag(new HistorySingleFragment(), getResources().getString(R.string.history_single));
        adapter.addFrag(new HistoryDoubleFragment(), getResources().getString(R.string.history_doubles));
        adapter.addFrag(new HistoryValuesFragment(), getResources().getString(R.string.history_values));
        adapter.addFrag(new HistoryAwardsFragment(), getResources().getString(R.string.history_awards));
        adapter.addFrag(new HistoryCommitteeFragment(), getResources().getString(R.string.history_committee));
        viewPager.setAdapter(adapter);
    }


}
