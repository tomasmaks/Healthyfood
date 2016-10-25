package my.food.tomas.healthyfood.dependencies;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Tomas on 23/10/2016.
 */


    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CustomScope {
    }
