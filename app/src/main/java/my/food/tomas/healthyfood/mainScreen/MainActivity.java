package my.food.tomas.healthyfood.mainScreen;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import my.food.tomas.healthyfood.FoodApplication;
import my.food.tomas.healthyfood.R;
import my.food.tomas.healthyfood.data.AppRepository;
import my.food.tomas.healthyfood.data.local.models.RecipeSearchParams;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;
import my.food.tomas.healthyfood.detailsScreen.RecipeActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.OnMainFragmentListener {

    public static final String TAG = "MainActivity";

    private MainFragment mainFragment;
    private SearchView searchView;
    private MenuItem searchClearItem;
    private RecipeSearchParams recipeSearchParams;

    private String prevSearchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_host);
        initFragment(savedInstanceState);
       // recipeSearchParams = new RecipeSearchParams();
    }

    @Override
    public void onStartRecipeActivity(String id) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra(RecipeActivity.RECIPE_ID, id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        setupSearchView();
        searchClearItem = menu.findItem(R.id.action_search_clear);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_top:
                actionSortTop();
                item.setChecked(true);
                return true;
            case R.id.action_sort_tranding:
                actionSortTranding();
                item.setChecked(true);
                return true;
            case R.id.action_view_list:
                actionViewList();
                item.setChecked(true);
                return true;
            case R.id.action_view_table:
                actionViewTable();
                item.setChecked(true);
                return true;
            case R.id.action_search_clear:
                searchRecipe("");
                searchClearItem.setVisible(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                mainFragment = (MainFragment)getSupportFragmentManager().findFragmentByTag(MainFragment.TAG);
            }
            createFragment();
        }
    }

    private void createFragment() {
        mainFragment = MainFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mainFragment, MainFragment.TAG)
                .commit();
    }


    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRecipe(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    searchView.setQuery(prevSearchQuery, false);
                    searchClearItem.setVisible(false);
                } else if (prevSearchQuery != null && prevSearchQuery.length() > 0) {
                    searchClearItem.setVisible(true);
                }
            }
        });
    }

    private void searchRecipe(String query) {

    }

    private void actionSortTop() {

    }

    private void actionSortTranding() {

    }

    private void actionViewList() {

    }

    private void actionViewTable() {

    }

}
