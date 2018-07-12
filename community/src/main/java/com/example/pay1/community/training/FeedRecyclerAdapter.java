package com.example.pay1.community.training;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pay1.community.R;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private final FeedListPresenter presenter;
    private RecyclerViewClickListener listener;


    public FeedRecyclerAdapter(FeedListPresenter feedListPresenter , RecyclerViewClickListener listener) {
        this.presenter = feedListPresenter;
    this.listener=listener;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item_layout, parent, false);
        final FeedViewHolder mViewHolder = new FeedViewHolder(mView);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(FeedViewHolder viewHolder, int position) {
       presenter.onBindFeedRowViewAtPosition(position,viewHolder);
    }


    @Override
    public int getItemCount() {
        return presenter.getFeedListRowsCount();
    }


}
