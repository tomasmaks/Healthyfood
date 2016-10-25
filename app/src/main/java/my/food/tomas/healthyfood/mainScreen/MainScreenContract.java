package my.food.tomas.healthyfood.mainScreen;

import my.food.tomas.healthyfood.BasePresenter;
import my.food.tomas.healthyfood.BaseView;

/**
 * Created by Tomas on 25/10/2016.
 */

public class MainScreenContract {

    interface View extends BaseView<Presenter> {

       // void showPosts(List<Post> posts);

        void showError(String message);

        void showComplete();
    }

    interface Presenter extends BasePresenter {

        void loadPost();

        void loadPostFromRemoteDatatore();
    }
}