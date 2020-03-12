package com.example.android.dagger.di

import com.example.android.dagger.storage.FakeStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module

/**
 * Overrides StorageModule (which provided SharedPreferencesStorage) in android tests
 *
 * For integration tests, a good practice is to create a TestApplicationComponent meant for testing,
 * because production and testing use a different component configuration.
 *
 * Here, the difference is that we will use a FakeStorage in UserManager, instead of SharedPreferencesStorage.
 * So we have to swap the StorageModule for a different one that uses FakeStorage
 */
@Module
abstract class TestStorageModule {

    /**
     * Makes Dagger provide FakeStorage when a Storage type is requested
     *
     *  Instead of declaring the method with SharedPreferencesStorage as a parameter,
     * for the TestStorageModule, we pass FakeStorage as parameter.
     * Because of how @Binds, TestAppComponent will use this implementation of Storage.
     */
    @Binds
    abstract fun provideStorage(storage: FakeStorage): Storage
}