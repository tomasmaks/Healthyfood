package my.food.tomas.healthyfood.mainScreen;

import android.util.Log;

import java.util.List;

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
    private Subscription mSubscription;
    private CompositeSubscription compositeSubscription;
    private AppRemoteDataStore appRemoteDataStore;
    private MainScreenContract.View mView;
    private RecipeSearchParams recipeSearchParams;
    String query;

    public MainScreenPresenter(AppRemoteDataStore appRemoteDataStore, MainScreenContract.View mView) {
        this.appRemoteDataStore = appRemoteDataStore;
        this.mView = mView;
        mView.setPresenter(this);
    }

//    @Override
//    public void loadRecipesList(String query) {
//        checkCompositeSubscription();
//        compositeSubscription.add(this.appRemoteDataStore.getRecipesList(query)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(new Subscriber <List<RecipesList>>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "Complete");
//                        mView.showComplete();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, e.toString());
//                        mView.showError(e.toString());
//                    }
//
//                    @Override
//                    public void onNext(List<RecipesList> recipesList) {
//                        mView.showRecipesList(recipesList);
//                    }
//                })
//        );
//    }

    @Override
    public void loadRecipesList(String query) {
        new AppRemoteDataStore().getRecipesList(query).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<RecipesList>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Complete");
                        mView.showComplete();
                        //loadRecipesList(query);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.toString());
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(RecipesList recipesList) {
                        mView.showRecipesList(recipesList);
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
        if (mSubscription != null && mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }

    private void checkCompositeSubscription() {
        if (this.compositeSubscription == null || this.compositeSubscription.isUnsubscribed())
            this.compositeSubscription = new CompositeSubscription();
    }
}