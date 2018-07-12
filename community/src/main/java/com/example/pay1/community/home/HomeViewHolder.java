package com.example.pay1.community.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.pay1.community.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HomeViewHolder extends RecyclerView.ViewHolder implements RowView {

    TextView title , time , date;
    ImageView icon,right;
    ImageView thumbnail;
    ConstraintLayout textLayout;

    public HomeViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.home_item_title);
        time = itemView.findViewById(R.id.home_item_time);
        date = itemView.findViewById(R.id.home_item_date);
        icon = itemView.findViewById(R.id.home_item_icon);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        right=itemView.findViewById(R.id.right_icon);
        textLayout=itemView.findViewById(R.id.textLayout);

    }


    @Override
    public void setTitle(String titlee) {
        title.setText(titlee);
    }

    @Override
    public void setIcon(String imageUrl) {
        new DownloadImageTask(icon)
                .execute(imageUrl);

    }

    @Override
    public void setDate(String dat) {
        date.setText(dat);

    }

    @Override
    public void setTime(String tim) {
        time.setText(tim);

    }

    @Override
    public void setThumbnail(String videoURL) {
textLayout.setVisibility(View.GONE);

    thumbnail.setVisibility(View.VISIBLE);
        try
        {
           String videoId=extractYoutubeId(videoURL);

            Log.e("VideoId is->","" + videoId);

            String img_url="http://img.youtube.com/vi/"+videoId+"/0.jpg"; // this is link which will give u thumnail image of that video

            // picasso jar file download image for u and set image in imagview

            Picasso.get()
                    .load(img_url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(thumbnail);

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


    public String extractYoutubeId(String url) throws MalformedURLException {
        String query = new URL(url).getQuery();
        String[] param = query.split("&");
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }

}
