package my.food.tomas.healthyfood.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import my.food.tomas.healthyfood.FoodApplication;
import my.food.tomas.healthyfood.data.local.AppLocalDataStore;
import my.food.tomas.healthyfood.data.local.models.RecipeSearchParams;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Tomas on 25/10/2016.
 */

//public class AppRepository implements AppDataStore {
//
//    private AppLocalDataStore mAppLocalDataStore;
//    private AppRemoteDataStore mAppRemoteDataStore;
//    RecipeSearchParams recipeSearchParams;
//
//    @Inject
//    Retrofit retrofit;
//
//    @Inject
//    public AppRepository(AppRemoteDataStore mAppRemoteDataStore, Retrofit retrofit) {
//        this.mAppRemoteDataStore = mAppRemoteDataStore;
//        this.retrofit = retrofit;
//    }
//
////    @Override
////    public Observable<List<RecipesList>> getRecipesList(String query) {
////        return retrofit.create(AppRemoteDataStore.Food2ForkApi.class)
////                .getRecipesList(
////                        FoodApplication.API_KEY,
////                        "pizza",
////                        "r",
////                        1
////
////                )
////                .subscribeOn(Schedulers.io());
////    }
//
//
//
//    }
