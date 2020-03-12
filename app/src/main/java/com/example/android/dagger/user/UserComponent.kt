package com.example.android.dagger.user

import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.settings.SettingsActivity
import dagger.Subcomponent

/**
 * Definition of a Dagger subcomponent
 *
 * Created because we need to use the same instance of UserDataRepository across MainActivity and SettingsActivity,
 * but can't scope UserDataRepository to AppComponent (with @Singleton), because user data is specific to a logged-in user.
 * (If the user logs out/unregisters, we don't want to keep the same instance of UserDataRepository in memory.)
 *
 * So this subcomponent:
 *   - has its lifetime attached to UserManager, not to an Activity lifetime
 * (unlike LoginComponent and RegistrationComponent, which are managed by their Activities)
 *   - lives as long as the user is logged in.
 * (UserManager handles registrations, log in and log out attempts)
 *   - will inject all the Activities that can be accessed after the user is logged in (i.e. MainActivity and SettingsActivity).
 *   - can inject more than one Activity, and the number of Activities could potentially increase.
 *
 *   @LoggedUserScope is the scope annotation that the UserComponent uses.
 *   Classes annotated with @LoggedUserScope will have a unique instance in this Component.
 */
@LoggedUserScope
@Subcomponent
interface UserComponent {

    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)
    fun inject(activity: SettingsActivity)
}