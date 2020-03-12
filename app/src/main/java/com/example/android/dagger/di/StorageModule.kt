package com.example.android.dagger.di

import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module

/**
 * This module tells Dagger how to provide instances of Storage,
 * using the @Provides or @Binds annotations to define how to provide dependencies.
 *
 * Because of @Binds, StorageModule needs to be an abstract class.
 */
@Module
abstract class StorageModule {

    /**
     * Makes Dagger provide SharedPreferencesStorage when a Storage type is requested
     *
     * @Binds tells Dagger which implementation it needs to use when providing an interface.
     *
     * @Binds must annotate an abstract function (since it's abstract, it doesn't contain any code
     * and the class needs to be abstract too).
     */
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage


    /**
     * @Provides annotation/function tells Dagger how to provide an instance of a class inside a Dagger module.
     *
     * The return type of the @Provides function tells Dagger what type is added to the graph.
     * The parameters are the dependencies that Dagger needs to satisfy before providing an instance of that type.
     *
     * Use the @Provides annotation in Dagger modules to tell Dagger how to provide:
     *   - Implementations of an interface
     *  (although @Binds is recommended because it generates less code and therefore it's more efficient)
     *   - Classes that your project doesn't own (e.g. instances of Retrofit)
     */

    // @Provides tell Dagger how to create instances of the type that this function
    // returns (i.e. Storage).
    // Function parameters are the dependencies of this type (i.e. Context).
//    @Provides
//    fun provideStorage(context: Context): Storage {
//        // Whenever Dagger needs to provide an instance of type Storage,
//        // this code (the one inside the @Provides method) will be run.
//        return SharedPreferencesStorage(context)
//    }
}