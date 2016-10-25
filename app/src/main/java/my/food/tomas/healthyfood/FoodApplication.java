package my.food.tomas.healthyfood;

import android.app.Application;

import my.food.tomas.healthyfood.dagger.component.AppComponent;
import my.food.tomas.healthyfood.dagger.component.DaggerAppComponent;
import my.food.tomas.healthyfood.dagger.module.AppModule;
import my.food.tomas.healthyfood.dagger.module.DataModule;


/**
 * Created by Tomas on 23/10/2016.
 */

public class FoodApplication extends Application {

    private static AppComponent mAppComponent;
    public static final String API_KEY = "13837e980aa8f2527d4a3154829215df";
    public static final String BASE_URL = "http://food2fork.com";

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule("http://jsonplaceholder.typicode.com/"))
                .build();
    }


    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}

