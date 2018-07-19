package com.example.pay1.community.TRAINING;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.pay1.community.AEPS.AepsActivity;
import com.example.pay1.community.API.APIInterface;
import com.example.pay1.community.API.response.FeedResource;
import com.example.pay1.community.BLOG.BlogActivity;
import com.example.pay1.community.HOME.HomeActivity;
import com.example.pay1.community.R;
import com.example.pay1.community.ResourceList;
import com.example.pay1.community.UPDATE.Update;
import com.example.pay1.community.UPDATE.UpdateActivity;
import com.example.pay1.community.database.DatabaseManager;
import com.example.pay1.community.database.entity.ResourceEntity;
import com.example.pay1.community.database.entity.TrainingEntity;

import java.io.Serializable;
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

public class TrainingActivity extends AppCompatActivity
{

    List<Training> trainingList = new ArrayList<Training>();
    private DrawerLayout mDrawerLayout;
    public RecyclerView recyclerView;
    String ts;
    Bundle bundle = new Bundle();
    List<ResourceList> resourceList = new ArrayList<ResourceList>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_mat);

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
                            Intent i = new Intent(TrainingActivity.this, HomeActivity.class);
                            startActivity(i);

                        } else if (i2 == R.id.compUpd) {
                            Intent i1 = new Intent(TrainingActivity.this, UpdateActivity.class);
                            startActivity(i1);

                        } else if (i2 == R.id.blog) {
                            Intent i3 = new Intent(TrainingActivity.this, BlogActivity.class);
                            startActivity(i3);

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
                int[] id = new int[feedResources.size()];
                int[] visibility = new int[feedResources.size()];
                int[] resRep = new int[feedResources.size()];

                for (int i = 0; i < feedResources.size(); i++)
                {

                    titles[i] = feedResources.get(i).getTitle();
                    titleURL[i] = feedResources.get(i).getRes().get(0).getResUrl();
                    iconURL[i] = feedResources.get(i).getSmallIcon().get(0).getResUrl();
                    type[i] = feedResources.get(i).getFeedType();
                    id[i] = feedResources.get(i).getId();
                    visibility[i] = feedResources.get(i).getVisibility();
                    resRep[i]=feedResources.get(i).getResourceRepresentationType();
                    Training fd = new Training(id[i],titles[i], titleURL[i], iconURL[i], type[i], ts,visibility[i],resRep[i]);
                    //                    trainingList.add(fd);

                    for(int j=0;j<feedResources.get(i).sizeRes();j++)
                    {

                        ResourceList rs = new ResourceList();

                        rs.setId(feedResources.get(i).getId());
                        rs.setResource_representation_type(feedResources.get(i).getResourceRepresentationType());
                        rs.setRes_id(feedResources.get(i).getRes().get(j).getResId());
                        rs.setRes_type(   feedResources.get(i).getRes().get(j).getResType());
                        rs.setRes_url(feedResources.get(i).getRes().get(j).getResUrl());

                        DatabaseManager.getInstance(getApplicationContext()).insertResouce(rs)
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.d("resource insert","success");
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                    }
                                });



                    }
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
                .subscribe(new Observer<TrainingEntity>() {
                    @Override public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(TrainingEntity entry) {

                        Training sd=new Training(entry.getId(),entry.getTitle(), entry.getTitleUrl(), entry.getIconUrl(), entry.getType(), ts,entry.getVisibility(),entry.getResource_representation_type());
                        trainingList.add(sd);
                        //Log.d("testing",entry.getTitle());

                    }
                    @Override public void onError(Throwable e) { }
                    @Override public void onComplete() {




                        DatabaseManager.getInstance(getApplicationContext()).getAllResourceEntries().subscribe(new Observer<ResourceEntity>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResourceEntity resourceEntity) {

                                ResourceList rs = new ResourceList();
                                rs.setId(resourceEntity.getId());
                                rs.setRes_id(resourceEntity.getRes_id());
                                rs.setRes_type(resourceEntity.getRes_type());
                                rs.setRes_url(resourceEntity.getRes_url());
                                rs.setResource_representation_type(resourceEntity.getResource_representation_type());

                                resourceList.add(rs);

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("get res",e.toString());

                            }

                            @Override
                            public void onComplete() {
                                Log.d("trainingList", String.valueOf(trainingList.isEmpty()));


                                Log.d("feedlist", String.valueOf(trainingList.isEmpty()));

                                // 1. get a reference to recyclerView
                                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

                                // 2. set layoutManger
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                                // 3. define click action and create an adapter

                                TrainingListPresenter trainingListPresenter = new TrainingListPresenter(trainingList);
                                TrainingRecyclerAdapter mAdapter = new TrainingRecyclerAdapter(trainingListPresenter,new RecyclerViewClickListener(){
                                    @Override
                                    public void onItemClick(View v, int position)
                                    {

                                        if(!resourceList.isEmpty())
                                        {
                                            Log.d("testing", "clicked position:" + position);
                                            String tf = String.valueOf(trainingList.get(position).getId()) + "," + String.valueOf(trainingList.get(position).getResource_representation_type()) + "," + trainingList.get(position).getTitle();
                                            Log.d("test data", tf);
                                            ArrayList<String> rees = new ArrayList<String>();
                                            //   if (trainingList.get(position).getTitleUrl().contains(".jpg") || trainingList.get(position).getTitleUrl().contains(".jpeg")) {
                                            if (trainingList.get(position).getResource_representation_type() == 2) {
                                                rees.add(trainingList.get(position).getTitleUrl());
                                                Intent intent = new Intent(getApplicationContext(), AepsActivity.class);

                                                Log.d("#@", String.valueOf(rees.isEmpty()));

                                                bundle.putSerializable("ARRAYLIST", (Serializable) rees);
                                                intent.putExtra("BUNDLE",bundle);

                                                startActivity(intent);

                                            } else if (trainingList.get(position).getResource_representation_type() == 3) {

                                                for (int j = 0; j < resourceList.size(); j++)

                                                {
                                                    if (resourceList.get(j).getId() == trainingList.get(position).getId())
                                                        rees.add(resourceList.get(j).getRes_url());
                                                }

                                                Intent intent = new Intent(getApplicationContext(), AepsActivity.class);
                                                Log.d("#@1", String.valueOf(rees.isEmpty()));
                                                bundle.putSerializable("ARRAYLIST",rees);
                                                intent.putExtra("BUNDLE",bundle);

                                                startActivity(intent);

                                            } else {
                                                String url = trainingList.get(position).getTitleUrl();
                                                String HTTP = "http://";
                                                if (!url.startsWith(HTTP)) {
                                                    url = HTTP + url;
                                                }
                                                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                                startActivity(intent1);
                                            }

                                        }


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
                                });

                               
                    }
                }
