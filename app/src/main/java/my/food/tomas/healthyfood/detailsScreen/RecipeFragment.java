package my.food.tomas.healthyfood.detailsScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import my.food.tomas.healthyfood.R;
import my.food.tomas.healthyfood.data.local.models.Recipe;
import my.food.tomas.healthyfood.data.local.models.RecipeGet;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;

import static my.food.tomas.healthyfood.detailsScreen.RecipeActivity.RECIPE_ID;


/**
 * Created by Tomas on 31/10/2016.
 */

public class RecipeFragment extends Fragment implements RecipeContract.View {

    public static final String TAG = "RecipeFragment";

    @Inject
    AppRemoteDataStore appRemoteDataStore;

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
    @Bind(R.id.share_fab)
    FloatingActionButton shareFab;

    private String recipeId;
    private Recipe recipe;
    private RecipeContract.Presenter recipePresenter;

    public static RecipeFragment newInstance(String id) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(RECIPE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public RecipeFragment() {

    }

    private void readArguments() {
        Bundle args = getArguments();
        recipeId = "";
        if (args != null) {
            recipeId = args.getString(RECIPE_ID);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
        new RecipePresenter(appRemoteDataStore, this);
        readArguments();
        recipePresenter.loadRecipeDetails(recipeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void showRecipeDetails(RecipeGet recipeGet) {
        recipe = recipeGet.getRecipe();
        if (recipe != null) {
            titleView.setText(Html.fromHtml(String.format("<a href=\"%s\">%s</a>", recipe.getSourceUrl(), recipe.getTitle())));
            titleView.setMovementMethod(LinkMovementMethod.getInstance());
            Picasso.with(imageView.getContext()).load(recipe.getImageUrl()).into(imageView);
            publisherView.setText(Html.fromHtml(String.format("<a href=\"%s\">%s</a>", recipe.getPublisherUrl(), recipe.getPublisherUrl())));
            publisherView.setMovementMethod(LinkMovementMethod.getInstance());
            rankView.setText(Html.fromHtml("Rank: " + String.format(Locale.getDefault(), "%.2f%%", Double.parseDouble(recipe.getSocialRank())) + ""));

            if (recipe.getIngredients() != null) {
                String ingredientsStr = "";
                for (int i = 0; i < recipe.getIngredients().size(); ++i) {
                    ingredientsStr += "\n - " + recipe.getIngredients().get(i) + ";\n";
                }
                ingredientsView.setText(ingredientsStr);

            }
        }

        shareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setText(recipe.getTitle() + ", " + recipe.getSourceUrl())
                        .getIntent(), getString(R.string.action_share)));
            }
        });



    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), "Error loading post, please check your internet connection!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
    }

    @Override
    public void setPresenter(RecipeContract.Presenter presenter) {
        recipePresenter = presenter;
    }

}
