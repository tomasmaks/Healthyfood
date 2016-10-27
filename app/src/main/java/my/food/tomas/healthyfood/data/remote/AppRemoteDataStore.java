package my.food.tomas.healthyfood.data.remote;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import my.food.tomas.healthyfood.FoodApplication;
import my.food.tomas.healthyfood.data.AppDataStore;
import my.food.tomas.healthyfood.data.local.AppLocalDataStore;
import my.food.tomas.healthyfood.data.local.models.RecipeGet;
import my.food.tomas.healthyfood.data.local.models.RecipeSearchParams;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Action1;

import static my.food.tomas.healthyfood.FoodApplication.API_KEY;

/**
 * Created by Tomas on 25/10/2016.
 */

public class AppRemoteDataStore implements AppDataStore {

    @Inject
    Retrofit retrofit;

//    @Inject
//    AppLocalDataStore appLocalDataStore;

    public AppRemoteDataStore() {
        FoodApplication.getAppComponent().inject(this);
    }

    @Override
    public Observable<List<RecipesList>> getRecipesList(RecipeSearchParams recipeSearchParams) {
        Log.d("REMOTE", "Loaded from remote");
        Observable<List<RecipesList>> call = null;
        if (retrofit != null) {
            Food2ForkApi apiService = retrofit.create(Food2ForkApi.class);
            if (recipeSearchParams == null) {
                recipeSearchParams = new RecipeSearchParams();
            }
            call = apiService.getRecipesList(
                    API_KEY,
                    recipeSearchParams.query,
                    recipeSearchParams.sort,
                    recipeSearchParams.page
            );
        }
        return call;
    }



    private interface Food2ForkApi {
        @GET("/api/search")
        Observable<List<RecipesList>> getRecipesList(@Query("key") String key, @Query("q") String q,
                                                                      @Query("sort") String sort, @Query("page") int page);

        @GET("/api/get")
        Observable<RecipeGet> getRecipeGet(@Query("key") String key, @Query("rId") String rId);
    }
}