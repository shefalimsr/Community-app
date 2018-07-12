package com.example.pay1.community.aeps;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.pay1.community.R;
import com.example.pay1.community.api.APIInterface;
import com.example.pay1.community.api.response.FeedResource;
import com.example.pay1.community.home.Home;
import com.example.pay1.community.home.HomeListPresenter;
import com.example.pay1.community.home.HomeRecyclerAdapter;

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
        Call<List<FeedResource>> jsonCall = service1.getSlider("3");

        jsonCall.enqueue(new Callback<List<FeedResource>>() {
            @Override
            public void onResponse(Call<List<FeedResource>> call, Response<List<FeedResource>> response)
            {
                List<FeedResource> feedResources=response.body();

                Log.d("response list", String.valueOf(response.body().size()));

//                String[] titles = new String[feedResources.size()];
//                String[] titleURL = new String[feedResources.size()];
//                String[] iconURL = new String[feedResources.size()];
//                String[] type = new String[feedResources.size()];
//
                for (int i = 0; i < feedResources.size(); i++)
                {
                        for(int j=0;j< feedResources.get(i).getRes().size(); j++)
                        {
                            images.add(feedResources.get(i).getRes().get(j).getResUrl());
                        }

//                    titles[i] = feedResources.get(i).getTitle();
//                    titleURL[i] = feedResources.get(i).getRes().get(0).getResUrl();
//                    iconURL[i] = feedResources.get(i).getSmallIcon().get(0).getResUrl();
//                    type[i] = String.valueOf(feedResources.get(i).getFeedType());
//                    Home fd = new Home(titles[i], titleURL[i], iconURL[i], type[i], ts);
//                    homeList.add(fd);
                    }
                myCustomPagerAdapter = new ImagePagerAdapter(AepsActivity.this, images);
                viewPager.setAdapter(myCustomPagerAdapter);
                }


            @Override
            public void onFailure(Call<List<FeedResource>> call, Throwable t) {
                Log.e("response error ", t.toString());
            }
        });



    }
}