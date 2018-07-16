package com.example.pay1.community.training;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.pay1.community.R;
import com.example.pay1.community.api.APIInterface;
import com.example.pay1.community.api.response.FeedResource;
import com.example.pay1.community.database.DatabaseManager;
import com.example.pay1.community.database.entity.FeedEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.LinearLayout.VERTICAL;

public class FeedActivity extends AppCompatActivity
{

    List<Feed> feedList = new ArrayList<Feed>();
    private DrawerLayout mDrawerLayout;
    public RecyclerView recyclerView;
    String ts;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran_mat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        Long tsLong = System.currentTimeMillis()/1000;
        ts = tsLong.toString();

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
                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        int i2 = menuItem.getItemId();
                        if (i2 == R.id.home) {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-home.com"));
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


        //-------------------------------------------- getting from API , inserting in database ---------------------------------------

        APItoDatabase();


        // ---------------------------------- getting from database, displaying in recyclerview ------------------------------------------

        DatabaseToRecycler();


    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    //-------------------------------------------- getting from API , inserting in database ---------------------------------------

    void APItoDatabase()
    {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://community.pay1.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service1 = retrofit1.create(APIInterface.class);
        Call<List<FeedResource>> jsonCall = service1.getList();

        jsonCall.enqueue(new Callback<List<FeedResource>>() {
            @Override
            public void onResponse(Call<List<FeedResource>> call, Response<List<FeedResource>> response)
            {

                deleteAll();

                List<FeedResource> feedResources = response.body();
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
                    Feed fd = new Feed(titles[i], titleURL[i], iconURL[i], type[i], ts);
                    //                    feedList.add(fd);

                    DatabaseManager.getInstance(getApplicationContext()).insertfeed(fd)
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onComplete() {
                                    Log.d("inserted","feed inserted");
                                }

                                @Override
                                public void onError(Throwable e) {
                                }
                            });


                }

            }

            @Override
            public void onFailure(Call<List<FeedResource>> call, Throwable t) {
                Log.e("response error ", t.toString());
            }
        });

    }



    //------------------------------------------- delete old data from database ----------------------------------------------------

    void deleteAll()
    {


        DatabaseManager.getInstance(getApplicationContext()).deleteAllFeedEntries().subscribe(new CompletableObserver() {
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


    // ---------------------------------- getting from database, displaying in recyclerview ------------------------------------------

    void DatabaseToRecycler()
    {

        DatabaseManager.getInstance(getApplicationContext()).getAllFeedEntries()
                .subscribe(new Observer<FeedEntity>() {
                    @Override public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(FeedEntity entry) {

                        Feed sd=new Feed(entry.getTitle(),entry.getTitleUrl(),entry.getIconUrl(),entry.getType(),ts);
                        feedList.add(sd);
                        //Log.d("testing",entry.getTitle());

                    }
                    @Override public void onError(Throwable e) { }
                    @Override public void onComplete() {

                        Log.d("feedlist", String.valueOf(feedList.isEmpty()));

                        // 1. get a reference to recyclerView
                        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

                        // 2. set layoutManger
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        // 3. define click action and create an adapter

                        FeedListPresenter feedListPresenter= new FeedListPresenter(feedList);
                        FeedRecyclerAdapter mAdapter = new FeedRecyclerAdapter(feedListPresenter,new RecyclerViewClickListener(){
                            @Override
                            public void onItemClick(View v, int position) {
                                Log.d("testing", "clicked position:" + position);
                                String url = feedList.get(position).getTitleUrl();
                                String HTTP = "http://";
                                if (!url.startsWith(HTTP)) {
                                    url = HTTP + url;
                                }
                                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent1);
                            }
                        });
                        // 4. set adapter
                        recyclerView.setAdapter(mAdapter);

                        // 5. set item animator to DefaultAnimator
                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), VERTICAL); //vertical line add
                        recyclerView.addItemDecoration(mDividerItemDecoration);
                    }
                });
    }

}
