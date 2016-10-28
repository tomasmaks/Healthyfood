package my.food.tomas.healthyfood.mainScreen;

import java.util.ArrayList;
import java.util.List;

import my.food.tomas.healthyfood.BasePresenter;
import my.food.tomas.healthyfood.BaseView;
import my.food.tomas.healthyfood.data.local.models.Recipe;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import rx.Observable;

/**
 * Created by Tomas on 25/10/2016.
 */

public class MainScreenContract {

    interface View extends BaseView<Presenter> {

        void showRecipesList(RecipesList recipesList);

        void showError(String message);

        void showComplete();

       // Observable<List<RecipesList>> getRecipesList();
    }

    interface Presenter extends BasePresenter {

        void loadRecipesList(String query);

       // void loadRecipesListFromRemoteDatastore(String query);
    }
}