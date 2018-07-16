package com.example.pay1.community.UPDATE;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pay1.community.R;

public class UpdateRecyclerAdapter extends RecyclerView.Adapter<UpdateViewHolder> {

    private final UpdateListPresenter presenter;
    RecyclerViewClickListener listener;

    public UpdateRecyclerAdapter(UpdateListPresenter updateListPresenter,RecyclerViewClickListener listener) {
        this.presenter = updateListPresenter;
        this.listener=listener;
    }

    @Override
    public UpdateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_item_layout, parent, false);
        final UpdateViewHolder mViewHolder = new UpdateViewHolder(mView);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder; }

    @Override
    public void onBindViewHolder(UpdateViewHolder viewHolder, int position) {
       presenter.onBindFeedRowViewAtPosition(position,viewHolder);
    }


    @Override
    public int getItemCount() {
        return presenter.getFeedListRowsCount();
    }


}
