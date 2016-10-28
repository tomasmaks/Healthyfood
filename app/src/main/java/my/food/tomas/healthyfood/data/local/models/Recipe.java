package my.food.tomas.healthyfood.data.local.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

/**
 * Created by Tomas on 25/10/2016.
 */

@Getter
public class Recipe implements Serializable {

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("title")
    private String title;

    @SerializedName("page")
    private int page;

    @SerializedName("social_rank")
    private String socialRank;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("source_url")
    private String sourceUrl;

    @SerializedName("f2f_url")
    private String f2fUrl;

    @SerializedName("publisher_url")
    private String publisherUrl;

    public Recipe(String publisher, String socialRank, String title, int page, String imageUrl, String sourceUrl, String f2fUrl, String publisherUrl) {
        this.publisher = publisher;
        this.socialRank = socialRank;
        this.title = title;
        this.page = page;
        this.imageUrl = imageUrl;
        this.sourceUrl = sourceUrl;
        this.f2fUrl = f2fUrl;
        this.publisherUrl = publisherUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSocialRank() {
        return socialRank;
    }

    public void setSocialRank(String socialRank) {
        this.socialRank = socialRank;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getF2fUrl() {
        return f2fUrl;
    }

    public void setF2fUrl(String f2fUrl) {
        this.f2fUrl = f2fUrl;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    public void setPublisherUrl(String publisherUrl) {
        this.publisherUrl = publisherUrl;
    }
}