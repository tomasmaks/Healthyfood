package my.food.tomas.healthyfood.mainScreen;

import java.util.List;

import my.food.tomas.healthyfood.BasePresenter;
import my.food.tomas.healthyfood.BaseView;
import my.food.tomas.healthyfood.data.local.models.RecipesList;

/**
 * Created by Tomas on 25/10/2016.
 */

public class MainScreenContract {

    interface View extends BaseView<Presenter> {

        void showRecipesList(List<RecipesList> recipesList);

        void showError(String message);

        void showComplete();
    }

    interface Presenter extends BasePresenter {

        void loadRecipesList();

        void loadRecipesListFromRemoteDatastore();
    }
}