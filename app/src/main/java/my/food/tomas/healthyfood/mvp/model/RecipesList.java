package my.food.tomas.healthyfood.mvp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Tomas on 23/10/2016.
 */

public class RecipesList {

    @Expose
    private String image_url;

    @Expose
    private String source_url;

    @Expose
    private String f2f_url;

    @Expose
    private String title;

    @Expose
    private String publisher;

    @Expose
    private String publisher_url;

    @Expose
    private float social_rank;

    @Expose
    private int page;


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getF2f_url() {
        return f2f_url;
    }

    public void setF2f_url(String f2f_url) {
        this.f2f_url = f2f_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher_url() {
        return publisher_url;
    }

    public void setPublisher_url(String publisher_url) {
        this.publisher_url = publisher_url;
    }

    public float getSocial_rank() {
        return social_rank;
    }

    public void setSocial_rank(float social_rank) {
        this.social_rank = social_rank;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
