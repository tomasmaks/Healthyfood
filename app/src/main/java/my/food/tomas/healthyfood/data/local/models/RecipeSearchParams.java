package my.food.tomas.healthyfood.data.local.models;

import java.io.Serializable;

/**
 * Created by Tomas on 25/10/2016.
 */

public class RecipeSearchParams implements Serializable {

    public String api;
    public String query;
    public String sort;
    public int page;

    public RecipeSearchParams() {
        query = "";
        sort = "r";
        page = 1;
    }

}