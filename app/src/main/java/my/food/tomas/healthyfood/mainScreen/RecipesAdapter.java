package my.food.tomas.healthyfood.mainScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import my.food.tomas.healthyfood.R;
import my.food.tomas.healthyfood.data.local.models.Recipe;
import my.food.tomas.healthyfood.data.local.models.RecipesList;

/**
 * Created by Tomas on 27/10/2016.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;

    private Context context;
    private ArrayList<Recipe> data;

    public RecipesAdapter(ArrayList<Recipe> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe item = data.get(position);
        holder.titleView.setText(item.getTitle());
        Picasso.with(holder.imageView.getContext()).load(item.getImageUrl()).into(holder.imageView);
        holder.publisherView.setText(item.getPublisher());
//        holder.rankView.setText(String.format("%.0f%%", item.getSocialRank()));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.recipe_title_view)
        TextView titleView;
        @Bind(R.id.recipe_image_view)
        ImageView imageView;
        @Bind(R.id.recipe_publisher_view)
        TextView publisherView;
        @Bind(R.id.recipe_rank_view)
        TextView rankView;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public void addRecipesList(ArrayList<Recipe> recipesList) {
        this.data.clear();
        this.data.addAll(recipesList);
        //notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
