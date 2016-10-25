package my.food.tomas.healthyfood.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import my.food.tomas.healthyfood.data.local.AppLocalDataStore;
import my.food.tomas.healthyfood.data.local.models.RecipeSearchParams;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Tomas on 25/10/2016.
 */

public class AppRepository implements AppDataStore {

    private AppLocalDataStore mAppLocalDataStore;
    private AppRemoteDataStore mAppRemoteDataStore;
    RecipeSearchParams recipeSearchParams;


    @Inject
    public AppRepository(AppLocalDataStore mAppLocalDataStore, AppRemoteDataStore mAppRemoteDataStore) {
        this.mAppLocalDataStore = mAppLocalDataStore;
        this.mAppRemoteDataStore = mAppRemoteDataStore;
    }

    @Override
    public Observable<List<RecipesList>> getRecipesList(RecipeSearchParams recipeSearchParams) {


        return null;
    }



    }
