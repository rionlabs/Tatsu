package org.rionlabs.tatsu.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.rionlabs.tatsu.work.*

object AppModule {

    fun get() = module {
        single {
            PreferenceManager(androidApplication())
        }

        single {
            SettingsManager(androidApplication())
        }

        single {
            SilentModeManager(androidApplication())
        }

        single {
            TimerController(androidApplication())
        }

        single {
            VibrationsManager(androidApplication())
        }
    }
}