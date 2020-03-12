package com.example.android.dagger.registration

import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.registration.enterdetails.EnterDetailsFragment
import com.example.android.dagger.registration.termsandconditions.TermsAndConditionsFragment
import dagger.Subcomponent

/**
 * Definition of a Dagger subcomponent
 *
 * Subcomponents are components that inherit and extend the object graph of a parent component.
 * All objects provided in the parent component will be provided in the subcomponent too.
 * An object from a subcomponent can depend on an object provided by the parent component.
 *
 * We use @ActivityScope as the scope annotation since @Singleton is already used by AppComponent.
 *
 * Classes annotated with @ActivityScope will have a unique instance in this Component.
 * Here, every time that an instance of RegistrationComponent provides an instance of
 * RegistrationViewModel, it will be the same one.
 */
@ActivityScope
@Subcomponent
interface RegistrationComponent {

    // Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: RegistrationActivity)
    fun inject(fragment: EnterDetailsFragment)
    fun inject(fragment: TermsAndConditionsFragment)
}