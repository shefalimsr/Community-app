package com.example.pay1.community.blog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;


import com.example.pay1.community.R;

public class BlogActivity extends AppCompatActivity {

    private WebView webView = null;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);


        mDrawerLayout = findViewById(R.id.drawer_view);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to UpdateEntity the UI based on the item selected
                        // For example, swap UI fragments here

                        mDrawerLayout.closeDrawers();
                        int i3 = menuItem.getItemId();
                        if (i3 == R.id.home) {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-home.com"));
                            startActivity(i);

                        } else if (i3 == R.id.trainMat) {
                            Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-training.com"));
                            startActivity(i2);

                        } else if (i3 == R.id.compUpd) {
                            Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-update.com"));
                            startActivity(i1);

                        } else if (i3 == R.id.chat) {
                            Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-aeps.com"));
                            startActivity(implicit);


                            return true;
                        } else {
                            return true;
                        }

                        return true;
                    }
                });


        this.webView = (WebView) findViewById(R.id.blogView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl("http://pay1.in/partners-blog/a-partnership-with-plethora-of-opportunities/");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

//            case R.id.trainMat :
        }
        return super.onOptionsItemSelected(item);
    }
}