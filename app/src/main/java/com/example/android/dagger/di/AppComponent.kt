package com.example.android.dagger.di

import com.example.android.dagger.registration.RegistrationActivity
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

    // Classes that can be injected by this Component
    fun inject(activity: RegistrationActivity)
}