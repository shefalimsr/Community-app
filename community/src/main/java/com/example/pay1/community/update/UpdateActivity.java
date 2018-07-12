package com.example.pay1.community.update;

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

import com.example.pay1.community.R;
import com.example.pay1.community.api.APIClient;
import com.example.pay1.community.api.APIInterface;
import com.example.pay1.community.api.response.FeedResource;
import com.example.pay1.community.api.response.FeedResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.LinearLayout.VERTICAL;

public class UpdateActivity extends AppCompatActivity
{

    List<Update> updateList = new ArrayList<Update>();
    private DrawerLayout mDrawerLayout;
    APIInterface apiInterface;
    List<FeedResponse> list;
    JsonArray jsonArray=new JsonArray();
    List<FeedResponse> feedResponses = new ArrayList<>();
    public String ts;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_updt);

        //-----------------------------------------------------------API-----------------------------------------------
        apiInterface = APIClient.getClient().create(APIInterface.class);


        Toolbar toolbar = findViewById(R.id.toolbar2);
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
                        int i2 = menuItem.getItemId();
                        if (i2 == R.id.home) {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-home.com"));
                            startActivity(i);

                        } else if (i2 == R.id.trainMat) {
                            Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.community-training.com"));
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
                        // Add code here to UpdateEntity the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });



        // this is data fro recycler view
        Long tsLong = System.currentTimeMillis()/1000;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ts = tsLong.toString();

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://community.pay1.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service1 = retrofit1.create(APIInterface.class);
        Call<List<FeedResource>> jsonCall = service1.getComp("1");

        jsonCall.enqueue(new Callback<List<FeedResource>>() {
            @Override
            public void onResponse(Call<List<FeedResource>> call, Response<List<FeedResource>> response)
            {
                List<FeedResource> feedResources=response.body();

                String[] titles = new String[feedResources.size()];
                String[] titleURL = new String[feedResources.size()];
                String[] iconURL = new String[feedResources.size()];
                String[] type = new String[feedResources.size()];

                for (int i = 0; i < feedResources.size(); i++)
                {
                    if(feedResources.get(i).getFeedType()==1) {
                        titles[i] = feedResources.get(i).getTitle();
                        titleURL[i] = feedResources.get(i).getRes().get(0).getResUrl();
                        iconURL[i] = feedResources.get(i).getSmallIcon().get(0).getResUrl();
                        type[i] = String.valueOf(feedResources.get(i).getFeedType());
                        Update fd = new Update(titles[i], titleURL[i], iconURL[i], type[i], ts);
                        updateList.add(fd);
                    }

                }

                // 1. get a reference to recyclerView
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.UpdateRecyclerView);

                // 2. set layoutManger
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                // 3. create an adapter
                UpdateListPresenter updateListPresenter = new UpdateListPresenter(updateList);
                UpdateRecyclerAdapter mAdapter = new UpdateRecyclerAdapter( updateListPresenter,new com.example.pay1.community.update.RecyclerViewClickListener(){
                    @Override
                    public void onItemClick(View v, int position) {
                        Log.d("testing", "clicked position:" + position);
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(updateList.get(position).getTitleUrl()));
                        startActivity(intent);
                    }
                });



                // 4. set adapter
                recyclerView.setAdapter(mAdapter);

                // 5. set item animator to DefaultAnimator
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), VERTICAL); //vertical line add
                recyclerView.addItemDecoration(mDividerItemDecoration);

            }

            @Override
            public void onFailure(Call<List<FeedResource>> call, Throwable t) {
                Log.e("response error ", t.toString());
            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
