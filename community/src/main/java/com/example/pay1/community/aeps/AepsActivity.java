package com.example.pay1.community.aeps;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.pay1.community.R;
import com.example.pay1.community.api.APIInterface;
import com.example.pay1.community.api.response.FeedResource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AepsActivity extends AppCompatActivity {
    ViewPager viewPager;
    List<String> images = new ArrayList<>();
   ImagePagerAdapter myCustomPagerAdapter;
   public String ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeps);

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy:HH:mm");

        Calendar cal = Calendar.getInstance();
        ts = dateFormat.format(cal.getTime());
        //   System.out.println(dateFormat.format(cal));

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
                List<FeedResource> feedResources=response.body();

                Log.d("response list", String.valueOf(response.body().size()));

                for (int i = 0; i < feedResources.size(); i++)
                {
                        for(int j=0;j< feedResources.get(i).getRes().size(); j++)
                        {
                            images.add(feedResources.get(i).getRes().get(j).getResUrl());
                        }
//
//                    DatabaseManager.getInstance(getApplicationContext()).insertfeed(fd)
//                            .subscribe(new CompletableObserver() {
//                                @Override
//                                public void onSubscribe(Disposable d) {
//                                }
//
//                                @Override
//                                public void onComplete() {
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                }
//                            });

                }
                myCustomPagerAdapter = new ImagePagerAdapter(AepsActivity.this, images);
                viewPager.setAdapter(myCustomPagerAdapter);
                }

            @Override
            public void onFailure(Call<List<FeedResource>> call, Throwable t) {
                Log.e("response error ", t.toString());
            }
        });

//        DatabaseManager.getInstance(getApplicationContext()).getAllEntries()
//                .subscribe(new Observer<FeedEntity>() {
//                    @Override public void onSubscribe(Disposable d) { }
//                    @Override
//                    public void onNext(FeedEntity entry) {
//
//                        Feed sd=new Feed(entry.getTitle(),entry.getTitleUrl(),entry.getIconUrl(),entry.getType(),ts);
//                        feedList.add(sd);
//                        Log.d("testing",entry.getTitle());
//
//                    }
//                    @Override public void onError(Throwable e) { }
//                    @Override public void onComplete() {
//
//                        Log.d("feedlist", String.valueOf(feedList.isEmpty()));
//
//                        // 1. get a reference to recyclerView
//                        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//
//
//                        Log.d("feedlist", String.valueOf(feedList.isEmpty()));
//                        // 2. set layoutManger
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//                        // 3. define click action and create an adapter
//
//                        FeedListPresenter feedListPresenter= new FeedListPresenter(feedList);
//                        FeedRecyclerAdapter mAdapter = new FeedRecyclerAdapter(feedListPresenter,new RecyclerViewClickListener(){
//                            @Override
//                            public void onItemClick(View v, int position) {
//                                Log.d("testing", "clicked position:" + position);
//                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(feedList.get(position).getTitleUrl()));
//                                startActivity(intent);
//                            }
//                        });
//                        // 4. set adapter
//                        recyclerView.setAdapter(mAdapter);
//
//                        // 5. set item animator to DefaultAnimator
//                        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//                        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), VERTICAL); //vertical line add
//                        recyclerView.addItemDecoration(mDividerItemDecoration);
//                    }
//                });

    }
}