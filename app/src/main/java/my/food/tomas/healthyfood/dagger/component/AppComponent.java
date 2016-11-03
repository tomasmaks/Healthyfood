package my.food.tomas.healthyfood.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import my.food.tomas.healthyfood.dagger.module.AppModule;
import my.food.tomas.healthyfood.dagger.module.DataModule;
import my.food.tomas.healthyfood.data.remote.AppRemoteDataStore;
import my.food.tomas.healthyfood.detailsScreen.RecipeActivity;
import my.food.tomas.healthyfood.detailsScreen.RecipeFragment;
import my.food.tomas.healthyfood.mainScreen.MainActivity;
import my.food.tomas.healthyfood.mainScreen.MainFragment;

/**
 * Created by Tomas on 25/10/2016.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(MainFragment mainFragment);
    void inject(RecipeActivity activity);
    void inject(RecipeFragment recipeFragment);
    void inject(AppRemoteDataStore appRemoteDataStore);
}