package my.food.tomas.healthyfood.detailsScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import my.food.tomas.healthyfood.R;

/**
 * Created by Tomas on 27/10/2016.
 */

public class RecipeActivity extends AppCompatActivity {

    public static final String TAG = "RecipeActivity";
    public static final String RECIPE_ID = "recipe_id";

    private RecipeFragment recipeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_host);
        initFragment(savedInstanceState);
        setupActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                recipeFragment = (RecipeFragment)getSupportFragmentManager().findFragmentByTag(RecipeFragment.TAG);
            }
            createFragment();
        }
    }

    private void createFragment() {
        String recipeId = "";
        if (getIntent().getExtras() != null) {
            recipeId = getIntent().getExtras().getString(RECIPE_ID, "");
        }
        recipeFragment = RecipeFragment.newInstance(recipeId);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, recipeFragment, RecipeFragment.TAG)
                .commit();
    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
