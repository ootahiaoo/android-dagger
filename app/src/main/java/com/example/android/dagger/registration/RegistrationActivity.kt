/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.dagger.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.dagger.MyApplication
import com.example.android.dagger.R
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.enterdetails.EnterDetailsFragment
import com.example.android.dagger.registration.termsandconditions.TermsAndConditionsFragment
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    /**
     * Stores an instance of RegistrationComponent so that its Fragments can access it
     * Doesn't need to be provided by Dagger (so no @Inject annotation)
     *
     * RegistrationComponent is attached to the lifetime of RegistrationActivity in order to:
     *   - share the same instance of the RegistrationViewModel between the registration Activity and Fragments.
     *   - have different instances of RegistrationViewModel whenever there's a new registration flow.
     */
    lateinit var registrationComponent: RegistrationComponent

    // Field injection example
    // @Inject annotated fields will be provided by Dagger
    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        // Creates an instance of Registration component by grabbing the factory from the app graph
        registrationComponent = (application as MyApplication).appComponent.registrationComponent().create()
        // Injects this activity to the just created registration component
        // (to perform field injection and populate the fields annotated with @Inject)
        registrationComponent.inject(this)

        // When using Activities, inject Dagger in the Activity's onCreate method before calling
        // super.onCreate to avoid issues with fragment restoration.
        // In super.onCreate, an Activity during the restore phase will attach fragments
        // that might want to access activity bindings.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, EnterDetailsFragment())
            .commit()
    }

    /**
     * Callback from EnterDetailsFragment when username and password has been entered
     */
    fun onDetailsEntered() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, TermsAndConditionsFragment())
            .addToBackStack(TermsAndConditionsFragment::class.java.simpleName)
            .commit()
    }

    /**
     * Callback from T&CsFragment when TCs have been accepted
     */
    fun onTermsAndConditionsAccepted() {
        registrationViewModel.registerUser()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
