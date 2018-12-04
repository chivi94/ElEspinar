package ivagonz.antroma.elespinar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.cons_and_preferences.Constants;
import ivagonz.antroma.elespinar.news.NewsFragment;
import ivagonz.antroma.elespinar.pageradapter.ViewPagerAdapter;

public class NewsActivity extends NavigationDrawerActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Intent intent;
    private int position;
    private NewsFragment newsTournamentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ly_news_activity);
        drawerLayout = (DrawerLayout) findViewById(R.id.news_activity_drawer_ly);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar, getResources().getString(R.string.news_name));
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

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public void onBackPressed() {
        WebView mWebView = (WebView) findViewById(R.id.webView);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (mWebView != null &&
                mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle args = null;

        String[] names = new String[]{getResources().getString(R.string.news_tournament), getResources().getString(R.string.news_sport),
                getResources().getString(R.string.news_communication), getResources().getString(R.string.news_did_you_know)};
        String[] url = new String[]{Constants.getNewsTournamentUrl(), Constants.getNewsSportUrl(), Constants.getNewsCommunicationUrl(), Constants.getNewsDidYouKnowUrl()};
        for (int i = 0; i < names.length; i++) {
            args = new Bundle();
            args.putString("url", url[i]);
            newsTournamentFragment = new NewsFragment();
            newsTournamentFragment.setArguments(args);
            adapter.addFrag(newsTournamentFragment, names[i]);
        }
        viewPager.setAdapter(adapter);
    }


}