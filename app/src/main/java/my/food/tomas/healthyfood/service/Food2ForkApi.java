package my.food.tomas.healthyfood.service;

import android.database.Observable;

import java.util.List;

import my.food.tomas.healthyfood.mvp.model.RecipeGet;
import my.food.tomas.healthyfood.mvp.model.RecipesList;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tomas on 22/10/2016.
 */

public interface Food2ForkApi {
    @GET("/api/search")
    Observable<List<RecipesList>> getRecipesList(@Query("key") String key, @Query("q") String q,
                                                 @Query("sort") String sort, @Query("page") int page);

    @GET("/api/get")
    Observable<RecipeGet> getRecipeGet(@Query("key") String key, @Query("rId") String rId);
}
