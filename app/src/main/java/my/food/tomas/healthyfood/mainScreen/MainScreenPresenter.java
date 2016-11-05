package my.food.tomas.healthyfood.mainScreen;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import my.food.tomas.healthyfood.data.local.models.Recipe;
import my.food.tomas.healthyfood.data.local.models.RecipeSearchParams;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Tomas on 25/10/2016.
 */

public class MainScreenPresenter implements MainScreenContract.Presenter {

    private static final String TAG = MainScreenPresenter.class.getSimpleName();
    private Subscription subscription;
    private CompositeSubscription compositeSubscription;
    private AppRemoteDataStore appRemoteDataStore;
    private MainScreenContract.View mainView;
    String query;


    public MainScreenPresenter(AppRemoteDataStore appRemoteDataStore, MainScreenContract.View view) {
        this.appRemoteDataStore = appRemoteDataStore;
        this.mainView = view;
        view.setPresenter(this);
    }

//    @Override
//    public void loadRecipesList(String query) {
//        new AppRemoteDataStore().getRecipesList(query).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(new Observer<RecipesList>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "Complete");
//                        mainView.showComplete();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, e.toString());
//                        mainView.showError(e.toString());
//                    }
//
//                    @Override
//                    public void onNext(RecipesList recipesList) {
//                        mainView.showRecipesList(recipesList);
//                    }
//                });
//    }

    @Override
    public void loadRecipesList(String query) {
        if (appRemoteDataStore == null){
            appRemoteDataStore = new AppRemoteDataStore();
        }

        subscription = appRemoteDataStore.getRecipesList(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<RecipesList>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Complete");
                        mainView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.toString());
                        mainView.showError(e.toString());
                    }

                    @Override
                    public void onNext(RecipesList recipesList) {
                        mainView.showRecipesList(recipesList);
                    }
                });
    }

    @Override
    public void subscribe() {
        loadRecipesList(query);
    }

    @Override
    public void unsubscribe() {
        //Unsubscribe Rx subscription
        if (subscription != null && subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}