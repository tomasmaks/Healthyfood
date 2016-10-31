package my.food.tomas.healthyfood.detailsScreen;

import my.food.tomas.healthyfood.BasePresenter;
import my.food.tomas.healthyfood.BaseView;
import my.food.tomas.healthyfood.data.local.models.RecipeGet;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import my.food.tomas.healthyfood.mainScreen.MainScreenContract;

/**
 * Created by Tomas on 31/10/2016.
 */

public class RecipeContract {


    interface View extends BaseView<RecipeContract.Presenter> {

        void showRecipeDetails(RecipeGet recipeGet);

        void showError(String message);

        void showComplete();

        }

    interface Presenter extends BasePresenter {

        void loadRecipeDetails(String query);

    }



}
