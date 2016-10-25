package my.food.tomas.healthyfood.application;

import android.app.Application;

import my.food.tomas.healthyfood.dependencies.ApiComponent;
import my.food.tomas.healthyfood.dependencies.NetworkComponent;
import my.food.tomas.healthyfood.dependencies.NetworkModule;
import my.food.tomas.healthyfood.utils.Constants;

/**
 * Created by Tomas on 23/10/2016.
 */

public class FoodApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    private NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();
    }

    public ApiComponent getmApiComponent() {
        return mApiComponent;
    }

}
