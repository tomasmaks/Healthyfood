package my.food.tomas.healthyfood.detailsScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import my.food.tomas.healthyfood.R;
import my.food.tomas.healthyfood.data.local.models.Recipe;


/**
 * Created by Tomas on 31/10/2016.
 */

public class RecipeFragment extends Fragment {

    public static final String TAG = "RecipeFragment";

    private View rootView;
    @Bind(R.id.recipe_title_view)
    TextView titleView;
    @Bind(R.id.recipe_image_view)
    ImageView imageView;
    @Bind(R.id.recipe_publisher_view)
    TextView publisherView;
    @Bind(R.id.recipe_rank_view)
    TextView rankView;
    @Bind(R.id.recipe_ingredients_view)
    TextView ingredientsView;

    private String recipeId;
    private Recipe recipe;

    public static RecipeFragment newInstance(String id) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
       // args.putString(RECIPE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public RecipeFragment() {

    }


}
