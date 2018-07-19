package com.example.pay1.community.AEPS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.pay1.community.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AepsActivity extends AppCompatActivity {
    ViewPager viewPager;
    ArrayList<String> images = new ArrayList<>();

   ImagePagerAdapter myCustomPagerAdapter;
   public String ts;
    Bundle args= new Bundle();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeps);


        intent = getIntent();
         args = intent.getBundleExtra("BUNDLE");
        ArrayList<String> img = (ArrayList<String>) args.getSerializable("ARRAYLIST");
        images.addAll(img);

        Log.d("image size", String.valueOf(images.size()));

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy:HH:mm");

        Calendar cal = Calendar.getInstance();
        ts = dateFormat.format(cal.getTime());

        //   System.out.println(dateFormat.format(cal));

        myCustomPagerAdapter = new ImagePagerAdapter(AepsActivity.this, images);
        viewPager.setAdapter(myCustomPagerAdapter);

//

    }
}