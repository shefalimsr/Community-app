package com.example.pay1.community.TRAINING;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pay1.community.R;

public class TrainingRecyclerAdapter extends RecyclerView.Adapter<TrainingViewHolder> {

    private final TrainingListPresenter presenter;
    private RecyclerViewClickListener listener;


    public TrainingRecyclerAdapter(TrainingListPresenter trainingListPresenter, RecyclerViewClickListener listener) {
        this.presenter = trainingListPresenter;
    this.listener=listener;
    }

    @Override
    public TrainingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item_layout, parent, false);
        final TrainingViewHolder mViewHolder = new TrainingViewHolder(mView);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(TrainingViewHolder viewHolder, int position) {
       presenter.onBindFeedRowViewAtPosition(position,viewHolder);
    }


    @Override
    public int getItemCount() {
        return presenter.getFeedListRowsCount();
    }


}
