package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.login.LoginComponent
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Definition of a Dagger component
 *
 * A @Component interface gives the information Dagger needs to generate the application graph at compile-time.
 * The parameter of the interface methods define what classes request injection.
 *
 * Include modules inside @Component annotation as "modules" parameter to let the graph know about them
 * & allow AppComponent access the information inside modules.
 *
 * In a more complex application, we could also have a NetworkModule that adds information on
 * how to provide a OkHttpClient, or how to configure Gson or Moshi, for example.
 *
 * Uses @Singleton scope annotation on the component and related classes to have unique instances of them
 */
@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    /**
     * Factory to create instances of the AppComponent
     *
     * Get the context, necessary to create SharedPreferencesStorage instance (used in StorageModule)
     * (context is already available when the AppComponent instance is created)
     */
    @Component.Factory
    interface Factory {
        /**
         * With @BindsInstance, the Context passed in will be available in the graph
         *
         * @BindsInstance tells Dagger that it needs to add that instance in the graph and whenever Context
         * is required, provide that instance.
         *
         * @BindsInstance is used for objects that are constructed outside of the graph (e.g. instances of Context).
         */
        fun create(@BindsInstance context: Context): AppComponent
    }

    /**
     * Two ways to interact with the Dagger graph:
     *   1. Declaring a function that returns a type allows retrieving types from the graph
     *   2. Declaring a function that returns Unit and takes a class as a parameter allows
     *  field injection in that class
     */

    // (1) Expose component factories from the graph (to be able to access them in the activities)
    fun registrationComponent(): RegistrationComponent.Factory
    fun loginComponent(): LoginComponent.Factory

    // (2) Classes that can be injected by this Component
    fun inject(activity: MainActivity)
}