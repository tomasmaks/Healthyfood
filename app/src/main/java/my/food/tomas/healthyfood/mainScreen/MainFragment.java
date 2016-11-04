package my.food.tomas.healthyfood.mainScreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import my.food.tomas.healthyfood.R;
import my.food.tomas.healthyfood.data.local.models.Recipe;
import my.food.tomas.healthyfood.data.local.models.RecipeSearchParams;
import my.food.tomas.healthyfood.data.local.models.RecipesList;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;

/**
 * Created by Tomas on 27/10/2016.
 */

public class MainFragment extends Fragment implements MainScreenContract.View {

    public static final String TAG = "MainFragment";

    private MainScreenContract.Presenter mainPresenter;
    private OnMainFragmentListener mainFragmentListener;

    String query;

    @Inject
    AppRemoteDataStore appRemoteDataStore;

    private View rootView;
    @Bind(R.id.recipes_recycler_view)
    RecyclerView recipesRecyclerView;
    @Bind(R.id.recipes_progress_bar)
    ProgressBar recipesProgressBar;
    @Bind(R.id.recipes_swipe_refresh)
    SwipeRefreshLayout recipesSwipeRefresh;
    @Bind(R.id.menu_yellow)
    FloatingActionMenu floatingActionMenu;
    @Bind(R.id.fab12)
    FloatingActionButton floatingActionButton12;
    @Bind(R.id.fab22)
    FloatingActionButton floatingActionButton22;
    @Bind(R.id.fab32)
    FloatingActionButton floatingActionButton32;

    private RecipesAdapter recipesAdapter;
    private RecipeSearchParams recipeSearchParams;
    private ArrayList<Recipe> recList;

    private List<FloatingActionMenu> menus = new ArrayList<>();

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
        recipeSearchParams.query = query;
        mainPresenter.loadRecipesList(query);

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
        recList = new ArrayList<>();
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        setupRecipesAdapter();
        setupSwipeRefresh();

        return rootView;
    }

    private void setupRecipesAdapter() {

        recipesAdapter = new RecipesAdapter(recList, getActivity());
        recipesAdapter.setOnItemClickListener(new RecipesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mainFragmentListener != null && recList != null) {
                    mainFragmentListener.onStartRecipeActivity(recList.get(position).getId());
                }
            }
        });
        recipesRecyclerView.setAdapter(recipesAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        floatingActionMenu.setClosedOnTouchOutside(true);
        menus.add(floatingActionMenu);

        floatingActionButton12.setOnClickListener(clickListener);
        floatingActionButton22.setOnClickListener(clickListener);
        floatingActionButton32.setOnClickListener(clickListener);

        createCustomAnimation();
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
        recipesProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showComplete() {
        Toast.makeText(getActivity(), "Completed loading", Toast.LENGTH_SHORT).show();
        recipesProgressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void setPresenter(MainScreenContract.Presenter presenter) {
        mainPresenter = presenter;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab12:
                    break;
                case R.id.fab22:
                    floatingActionButton22.setVisibility(View.VISIBLE);
                    break;
                case R.id.fab32:
                    floatingActionButton32.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    private void createCustomAnimation() {

        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                floatingActionMenu.getMenuIconView().setImageResource(floatingActionMenu.isOpened()
                        ? R.drawable.ic_close : R.drawable.fab_add);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        floatingActionMenu.setIconToggleAnimatorSet(set);
    }

    private void setupSwipeRefresh() {
        recipesSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (query == null && recList.size() == 0) {
                    mainPresenter.loadRecipesList(query);
                }
                recipesSwipeRefresh.setRefreshing(false);
            }
        });
    }

    public void setSearchQuery(String query) {
        if (recipeSearchParams == null) {
            recipeSearchParams = new RecipeSearchParams();
        }
        recipeSearchParams.query = query;
    }

    public void loadRecipesList(String query) {

        setupRecipesAdapter();
        mainPresenter.loadRecipesList(query);


    }

    public interface OnMainFragmentListener {
        public void onStartRecipeActivity(String id);
    }

}
