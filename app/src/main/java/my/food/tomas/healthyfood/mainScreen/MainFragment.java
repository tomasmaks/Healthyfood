package my.food.tomas.healthyfood.mainScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import my.food.tomas.healthyfood.data.local.models.RecipeSearchParams;

/**
 * Created by Tomas on 27/10/2016.
 */

public class MainFragment extends Fragment  {

    public static final String TAG = "MainFragment";

    private RecipeSearchParams recipeSearchParams;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        fragment.recipeSearchParams = new RecipeSearchParams();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }












    public interface OnMainFragmentListener {
        public void onStartRecipeActivity(String id);
    }
}
