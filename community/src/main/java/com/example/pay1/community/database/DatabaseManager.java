package com.example.pay1.community.database;

import android.content.Context;

import com.example.pay1.community.database.entity.FeedEntity;
import com.example.pay1.community.database.entity.HomEntity;
import com.example.pay1.community.database.entity.UpdateEntity;
import com.example.pay1.community.home.Home;
import com.example.pay1.community.training.Feed;
import com.example.pay1.community.update.Update;

import java.util.List;


import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DatabaseManager {
    private Context context;

    public static DatabaseManager getInstance(Context context) {
        return new DatabaseManager(context);
    }

    private DatabaseManager(Context context) {
        this.context = context;
    }

    public Observable<FeedEntity> getAllEntries() {
        return Observable.create(new ObservableOnSubscribe<FeedEntity>() {
            @Override
            public void subscribe(ObservableEmitter<FeedEntity> emitter) throws Exception {
                List<FeedEntity> feedEntity = ApplicationDatabase.getInstance(context).feedDao().getAll();
                for(FeedEntity en : feedEntity) {
                    if(!emitter.isDisposed()) emitter.onNext(en);
                }
                if(!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<FeedEntity>> getListOfAllEntries() {
        return Observable.create(new ObservableOnSubscribe<List<FeedEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<FeedEntity>> emitter) throws Exception {
                List<FeedEntity> feedEntity = ApplicationDatabase.getInstance(context).feedDao().getAll();
                if(!emitter.isDisposed()) {
                    emitter.onNext(feedEntity);
                }
                if(!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteAllEntries() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                ApplicationDatabase.getInstance(context).feedDao().deleteAll();
                if(!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insertfeed(final Feed feedi) {
        return  Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {


                FeedEntity feedEntity = new FeedEntity();
                feedEntity.setTitle(feedi.getTitle());
                feedEntity.setIconUrl(feedi.getIconUrl());
                feedEntity.setTimestamp(feedi.getTimestamp());
                feedEntity.setTitleUrl(feedi.getTitleUrl());
                feedEntity.setType(feedi.getType());


                ApplicationDatabase.getInstance(context).feedDao().insertSingle(feedEntity);
                if(!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Completable inserthome(final Home homi) {
        return  Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {


                HomEntity homEntity = new HomEntity();
                homEntity.setTitle(homi.getTitle());
                homEntity.setIconUrl(homi.getIconUrl());
                homEntity.setTimestamp(homi.getTimestamp());
                homEntity.setTitleUrl(homi.getTitleUrl());
                homEntity.setType(homi.getType());


                ApplicationDatabase.getInstance(context).homeDao().insertSingle(homEntity);
                if(!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Completable insertupdate(final Update update) {
        return  Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {


                UpdateEntity updateEntity = new UpdateEntity();
                updateEntity.setTitle(update.getTitle());
                updateEntity.setIconUrl(update.getIconUrl());
                updateEntity.setTimestamp(update.getTimestamp());
                updateEntity.setTitleUrl(update.getTitleUrl());
                updateEntity.setType(update.getType());


                ApplicationDatabase.getInstance(context).updateDao().insertSingle(updateEntity);
                if(!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
