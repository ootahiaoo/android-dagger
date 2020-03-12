package com.example.android.dagger.di

import dagger.Component
import javax.inject.Singleton

/**
 * Since production and testing use a different component configuration, we have to create
 * another component that acts as our AppComponent.
 *
 * The modules parameters include the AppSubcomponents module, that adds information about its subcomponents.
 * (RegistrationComponent, LoginComponent, UserComponent)
 *
 * Since we don't need Context for the test graph (the only dependency that required Context before
 * was SharedPreferencesStorage), there's no need to define a Factory for TestAppComponent.
 */
@Singleton
@Component(modules = [TestStorageModule::class, AppSubcomponents::class])
interface TestAppComponent : AppComponent