package my.food.tomas.healthyfood.detailsScreen;

import android.util.Log;

import my.food.tomas.healthyfood.data.local.models.Recipe;
import my.food.tomas.healthyfood.data.local.models.RecipeGet;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;
import my.food.tomas.healthyfood.mainScreen.MainScreenContract;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tomas on 31/10/2016.
 */

public class RecipePresenter implements RecipeContract.Presenter {

    private static final String TAG = RecipePresenter.class.getSimpleName();
    private Subscription subscription;
    private AppRemoteDataStore appRemoteDataStore;
    private RecipeContract.View recipeView;
    String id;

    public RecipePresenter(AppRemoteDataStore appRemoteDataStore, RecipeContract.View view) {
        this.appRemoteDataStore = appRemoteDataStore;
        this.recipeView = view;
        view.setPresenter(this);
    }

    @Override
    public void loadRecipeDetails(String id) {
        new AppRemoteDataStore().getRecipe(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<RecipeGet>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Complete");
                        recipeView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.toString());
                        recipeView.showError(e.toString());
                    }

                    @Override
                    public void onNext(RecipeGet recipeGet) {
                        recipeView.showRecipeDetails(recipeGet);
                    }
                });
    }


    @Override
    public void subscribe() {
        loadRecipeDetails(id);
    }

    @Override
    public void unsubscribe() {
        //Unsubscribe Rx subscription
        if (subscription != null && subscription.isUnsubscribed())
            subscription.unsubscribe();
    }


}
