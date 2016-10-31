package my.food.tomas.healthyfood.detailsScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Created by Tomas on 31/10/2016.
 */

public class RecipeFragment extends Fragment {

    public static final String TAG = "RecipeFragment";

    public static RecipeFragment newInstance(String id) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
       // args.putString(RECIPE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }
}
