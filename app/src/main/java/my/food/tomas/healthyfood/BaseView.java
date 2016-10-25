package my.food.tomas.healthyfood;

/**
 * Created by Tomas on 25/10/2016.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
}