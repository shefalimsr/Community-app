package com.example.pay1.community.TRAINING;

import java.util.List;

public class TrainingListPresenter {

    private final List<Training> trainingList;

    public TrainingListPresenter(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    public void onBindFeedRowViewAtPosition(int position, TrainingViewHolder rowView) {
        Training training = trainingList.get(position);
        rowView.setIcon(training.getIconUrl());
        rowView.setTitle(training.getTitle());
    }

    public int getFeedListRowsCount() {
        return trainingList.size();
    }



}
