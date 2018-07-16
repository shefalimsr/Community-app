package com.example.pay1.community.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.pay1.community.R;
import com.example.pay1.community.api.APIClient;
import com.example.pay1.community.api.APIInterface;
import com.example.pay1.community.api.response.FeedResource;
import com.example.pay1.community.database.DatabaseManager;
import com.example.pay1.community.database.entity.HomEntity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity
{
    APIInterface apiInterface;
    public  String ts;
    RecyclerView recyclerView;

    List<Home> homeList = new ArrayList<Home>();
    private DrawerLayout mDrawerLayout;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy.HH:mm");

    Date date= new Date(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_community_logo);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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




// -------------------------------------------------- Navigation drawer ---------------------------------------------------------


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
                        int i2 = menuItem.getItemId();
                        if (i2 == R.id.trainMat) {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-training.com"));
                            startActivity(i);

                        } else if (i2 == R.id.compUpd) {
                            Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-update.com"));
                            startActivity(i1);

                        } else if (i2 == R.id.blog) {
                            Intent i3 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-blog.com"));
                            startActivity(i3);

                        } else if (i2 == R.id.chat) {
                            Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-aeps.com"));
                            startActivity(implicit);

                            return true;
                        } else {
                            return true;
                        }

                        return true;
                    }
                });



        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy:HH:mm");

        Calendar cal = Calendar.getInstance();
        ts = dateFormat.format(cal.getTime());

      APItoDatabase();
      DatabaseToRecyclerView();

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

//            case R.id.trainMat :
        }
        return super.onOptionsItemSelected(item);
    }



    //------------------------------------------- delete old data from database ----------------------------------------------------

    void deleteAllHome()
    {


        DatabaseManager.getInstance(getApplicationContext()).deleteAllHomeEntries().subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.d("deleted feed","deleted");
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }



    // ---------------------------------- getting from database, displaying in recyclerview -------------------------------------------

    void DatabaseToRecyclerView()
    {

        DatabaseManager.getInstance(getApplicationContext()).getAllHomeEntries()
                .subscribe(new Observer<HomEntity>() {
                    @Override public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(HomEntity entry) {

                        Home sd=new Home(entry.getTitle(),entry.getTitleUrl(),entry.getIconUrl(),entry.getType(),ts);
                        homeList.add(sd);

                    }
                    @Override public void onError(Throwable e) { }
                    @Override public void onComplete() {

                        Log.d("homelist", String.valueOf(homeList.isEmpty()));

                        // 1. get a reference to recyclerView
                        recyclerView = (RecyclerView) findViewById(R.id.homeRecyclerView);

                        // 2. set layoutManger
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        // 3. create an adapter
                        HomeListPresenter homeListPresenter = new HomeListPresenter(homeList);
                        HomeRecyclerAdapter mAdapter = new HomeRecyclerAdapter(homeListPresenter, new com.example.pay1.community.home.RecyclerViewClickListener()
                        {
                            @Override
                            public void onItemClick(View v, int position)
                            {
                                Log.d("testing", "clicked position:" + position);
                                String url = homeList.get(position).getTitleUrl();
                                String HTTP = "http://";
                                if (!url.startsWith(HTTP)) {
                                    url = HTTP + url;
                                }
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                            }
                        });

                        // 4. set adapter
                        recyclerView.setAdapter(mAdapter);

                        // 5. set item animator to DefaultAnimator
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }
                });
    }




    //-------------------------------------------- getting from API , inserting in database ---------------------------------------

    void APItoDatabase()
    {

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://community.pay1.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service1 = retrofit1.create(APIInterface.class);
        Call<List<FeedResource>> jsonCall = service1.getList();

        jsonCall.enqueue(new Callback<List<FeedResource>>()
        {
            @Override
            public void onResponse(Call<List<FeedResource>> call, Response<List<FeedResource>> response)
            {
                List<FeedResource> feedResources = response.body();

                Log.d("response list", String.valueOf(response.body()));

                deleteAllHome();

                    String[] titles = new String[feedResources.size()];
                    String[] titleURL = new String[feedResources.size()];
                    String[] iconURL = new String[feedResources.size()];
                    int[] type = new int[feedResources.size()];

                    for (int i = 0; i < feedResources.size(); i++)
                    {

                        titles[i] = feedResources.get(i).getTitle();
                        titleURL[i] = feedResources.get(i).getRes().get(0).getResUrl();
                        iconURL[i] = feedResources.get(i).getSmallIcon().get(0).getResUrl();
                        type[i] = feedResources.get(i).getFeedType();
                        Home fd = new Home(titles[i], titleURL[i], iconURL[i], type[i], ts);
                        homeList.add(fd);
                        Log.d(" feed type ",String.valueOf(type[i]));


                        DatabaseManager.getInstance(getApplicationContext()).inserthome(fd)
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onComplete() {
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                    }
                                });
                    }

                }



            @Override
            public void onFailure(Call<List<FeedResource>> call, Throwable t)
            {
                Log.e("response error ", t.toString());
            }

        });
    }

}
