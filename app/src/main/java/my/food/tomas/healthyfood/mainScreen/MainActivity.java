package my.food.tomas.healthyfood.mainScreen;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import my.food.tomas.healthyfood.FoodApplication;
import my.food.tomas.healthyfood.R;
import my.food.tomas.healthyfood.data.AppRepository;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;

public class MainActivity extends AppCompatActivity implements MainFragment.OnMainFragmentListener {

    private SearchView searchView;
    private MenuItem searchClearItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_host);
        initFragment(savedInstanceState);
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






}
