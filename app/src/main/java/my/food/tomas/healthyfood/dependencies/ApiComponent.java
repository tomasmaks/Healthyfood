package my.food.tomas.healthyfood.dependencies;

import dagger.Component;
import my.food.tomas.healthyfood.MainActivity;

/**
 * Created by Tomas on 23/10/2016.
 */

@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void inject(MainActivity mainActivity);
}