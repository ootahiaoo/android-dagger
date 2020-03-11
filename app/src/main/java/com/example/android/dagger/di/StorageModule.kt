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
}