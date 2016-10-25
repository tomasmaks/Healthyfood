package my.food.tomas.healthyfood.mainScreen;

import android.util.Log;

import java.util.List;

import my.food.tomas.healthyfood.data.AppRepository;
import my.food.tomas.healthyfood.data.local.models.RecipeSearchParams;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tomas on 25/10/2016.
 */

public class MainScreenPresenter implements MainScreenContract.Presenter {

    private static final String TAG = MainScreenPresenter.class.getSimpleName();
    private Subscription mSubscription;
    private AppRemoteDataStore appRemoteDataStore;
    private MainScreenContract.View mView;
    private RecipeSearchParams recipeSearchParams;

    public MainScreenPresenter(AppRemoteDataStore appRemoteDataStore, MainScreenContract.View mView) {
        this.appRemoteDataStore = appRemoteDataStore;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loadRecipesList() {
        mSubscription = appRemoteDataStore.getRecipesList(recipeSearchParams)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<RecipesList>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Complete");
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.toString());
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(List<RecipesList> recipesList) {
                        mView.showRecipesList(recipesList);
                    }
                });
    }

    @Override
    public void loadRecipesListFromRemoteDatastore() {
        new AppRemoteDataStore().getRecipesList(recipeSearchParams).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<RecipesList>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Complete");
                        mView.showComplete();
                        loadRecipesList();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.toString());
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(List<RecipesList> recipesList) {
                        mView.showRecipesList(recipesList);
                    }
                });
    }

    @Override
    public void subscribe() {
        loadRecipesList();
    }

    @Override
    public void unsubscribe() {
        //Unsubscribe Rx subscription
        if (mSubscription != null && mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }
}