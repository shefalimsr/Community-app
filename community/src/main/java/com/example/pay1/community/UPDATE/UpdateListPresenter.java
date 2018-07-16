package com.example.pay1.community.UPDATE;

import java.util.List;

public class UpdateListPresenter {

    private final List<Update> updateList;

    public UpdateListPresenter(List<Update> updateList) {
        this.updateList = updateList;
    }

    public void onBindFeedRowViewAtPosition(int position, UpdateViewHolder rowView) {
        Update update = updateList.get(position);
        rowView.setIcon(update.getIconUrl());
        rowView.setTitle(update.getTitle());
    }

    public int getFeedListRowsCount() {
        return updateList.size();
    }



}
