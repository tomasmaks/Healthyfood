package my.food.tomas.healthyfood.mainScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import my.food.tomas.healthyfood.FoodApplication;
import my.food.tomas.healthyfood.R;
import my.food.tomas.healthyfood.data.local.models.Recipe;
import my.food.tomas.healthyfood.data.local.models.RecipeSearchParams;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;
import rx.Observable;

import static android.R.id.list;

/**
 * Created by Tomas on 27/10/2016.
 */

public class MainFragment extends Fragment implements MainScreenContract.View {

    public static final String TAG = "MainFragment";

    private OnMainFragmentListener mainFragmentListener;

    private MainScreenContract.Presenter mPresenter;
    public static final String API_KEY = "13837e980aa8f2527d4a3154829215df";
    public static final String API_Q = "pizza";
    public static final String API_S = "r";
    public static final int API_P = 1;

    @Inject
    AppRemoteDataStore appRemoteDataStore;

    private View rootView;
    @Bind(R.id.recipes_recycler_view)
    RecyclerView recipesRecyclerView;
//    @Bind(R.id.recipes_progress_bar)
//    ProgressBar recipesProgressBar;
//    @Bind(R.id.recipes_swipe_refresh)
//    SwipeRefreshLayout recipesSwipeRefresh;

    private RecipesAdapter recipesAdapter;
    private RecipeSearchParams recipeSearchParams;
    private ArrayList<Recipe> recList;
    private AppRemoteDataStore.Food2ForkApi mService;
   // ArrayList<String> list;


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        fragment.recipeSearchParams = new RecipeSearchParams();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());

        new MainScreenPresenter(appRemoteDataStore, this);
        mPresenter.loadRecipesList(API_Q);




    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mainFragmentListener = (OnMainFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnMainFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainFragmentListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);
        recipesRecyclerView = (RecyclerView) rootView.findViewById(R.id.recipes_recycler_view);
        recList = new ArrayList<>();
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recipesAdapter = new RecipesAdapter(recList);
        recipesRecyclerView.setAdapter(recipesAdapter);

        return rootView;
    }

    @Override
    public void showRecipesList(RecipesList recipesList) {
        recList = recipesList.getRecipes();
        recipesAdapter.addRecipesList(recList);
        recipesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), "Error loading post", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        Toast.makeText(getActivity(), "Completed loading", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setPresenter(MainScreenContract.Presenter presenter) {
        mPresenter = presenter;
    }

//    @Override
//    public Observable<List<RecipesList>> getRecipesList() {
//        return mService.getRecipesList(API_KEY, API_Q, API_S, API_P);
//    }


    public interface OnMainFragmentListener {
        public void onStartRecipeActivity(String id);
    }
}
