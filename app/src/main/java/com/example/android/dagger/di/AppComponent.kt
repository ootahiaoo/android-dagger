package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.registration.RegistrationActivity
import dagger.BindsInstance
import dagger.Component

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
 */
@Component(modules = [StorageModule::class])
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

    // Classes that can be injected by this Component
    fun inject(activity: RegistrationActivity)
}