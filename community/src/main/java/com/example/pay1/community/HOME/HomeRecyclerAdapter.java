package com.example.pay1.community.HOME;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pay1.community.R;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private final HomeListPresenter presenter;
    RecyclerViewClickListener listener;

    public HomeRecyclerAdapter(HomeListPresenter homeListPresenter,RecyclerViewClickListener listener) {
        this.presenter = homeListPresenter;
        this.listener=listener;
    }



    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_layout, parent, false);
        final HomeViewHolder mViewHolder = new HomeViewHolder(mView);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder viewHolder, int position) {
       presenter.onBindFeedRowViewAtPosition(position,viewHolder);
    }


    @Override
    public int getItemCount() {
        return presenter.getFeedListRowsCount();
    }


}
