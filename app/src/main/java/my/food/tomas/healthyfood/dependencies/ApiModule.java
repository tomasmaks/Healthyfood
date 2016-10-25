package my.food.tomas.healthyfood.dependencies;

import dagger.Module;
import dagger.Provides;
import my.food.tomas.healthyfood.service.Food2ForkApi;
import retrofit2.Retrofit;

/**
 * Created by Tomas on 23/10/2016.
 */

@Module
public class ApiModule {

    @Provides
    @CustomScope
    Food2ForkApi provideRepoService(Retrofit retrofit) {
        return retrofit.create(Food2ForkApi.class);
    }

}
