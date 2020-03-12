package com.example.android.dagger.user

import javax.inject.Scope

/**
 * This scope covers multiple activities but not all the application.
 *
 * We use it to make UserDataRepository be scoped to UserComponent,
 * so that both MainActivity and SettingsActivity can share the same instance of it.
 */
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class LoggedUserScope