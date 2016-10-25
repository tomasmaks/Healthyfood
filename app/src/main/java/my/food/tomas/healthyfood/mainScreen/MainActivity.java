package my.food.tomas.healthyfood.mainScreen;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements MainScreenContract.View, SwipeRefreshLayout.OnRefreshListener {

    private MainScreenContract.Presenter mPresenter;
    private ListView listView;
    private ArrayList list;
    private ArrayAdapter adapter;

    @Inject
    AppRemoteDataStore appRemoteDataStore;
    SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inject dependency
        FoodApplication.getAppComponent().inject(this);

        listView = (ListView) findViewById(R.id.my_list);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(this);
        list = new ArrayList<>();

        new MainScreenPresenter(appRemoteDataStore, this);
    }

    @Override
    public void showRecipesList(List recipesList) {
        for (int i = 0; i < recipesList.size(); i++) {
            list.add(recipesList.get(i).recipes());
        }
        //Create the array adapter and set it to list view
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error loading post", Toast.LENGTH_SHORT).show();
        if (swipeContainer != null)
            swipeContainer.post(new Runnable() {
                @Override
                public void run() {
                    swipeContainer.setRefreshing(false);
                }
            });
    }

    @Override
    public void showComplete() {
        Toast.makeText(this, "Completed loading", Toast.LENGTH_SHORT).show();

        if (swipeContainer != null)
            swipeContainer.post(new Runnable() {
                @Override
                public void run() {
                    swipeContainer.setRefreshing(false);
                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(MainScreenContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        mPresenter.loadRecipesListFromRemoteDatastore();
    }
}
