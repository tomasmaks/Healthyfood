package my.food.tomas.healthyfood.data.local.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tomas on 25/10/2016.
 */

public class RecipesList implements Serializable {

    private ArrayList<Recipe> recipes;

    public RecipesList(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

}