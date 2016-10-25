package my.food.tomas.healthyfood.data.remote;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import my.food.tomas.healthyfood.FoodApplication;
import my.food.tomas.healthyfood.data.AppDataStore;
import my.food.tomas.healthyfood.data.local.AppLocalDataStore;
import my.food.tomas.healthyfood.data.local.models.RecipeGet;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Tomas on 25/10/2016.
 */

public class AppRemoteDataStore implements AppDataStore {

    @Inject
    Retrofit retrofit;

    @Inject
    AppLocalDataStore appLocalDataStore;

    public AppRemoteDataStore() {
        FoodApplication.getAppComponent().inject(this);
    }


    private interface Food2ForkApi {
        @GET("/api/search")
        android.database.Observable<List<RecipesList>> getRecipesList(@Query("key") String key, @Query("q") String q,
                                                                      @Query("sort") String sort, @Query("page") int page);

        @GET("/api/get")
        android.database.Observable<RecipeGet> getRecipeGet(@Query("key") String key, @Query("rId") String rId);
    }
}