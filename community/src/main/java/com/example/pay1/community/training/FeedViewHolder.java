package com.example.pay1.community.training;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pay1.community.R;

import java.io.InputStream;

public class FeedViewHolder extends RecyclerView.ViewHolder implements RowView {

    private static final String TAG = "check";
    TextView title;
    ImageView icon;


    public FeedViewHolder(View itemView) {
        super(itemView);

        title = (TextView)itemView.findViewById(R.id.item_title);
        icon = (ImageView)itemView.findViewById(R.id.item_icon);
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

}
