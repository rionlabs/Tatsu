package org.rionlabs.tatsu.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.rionlabs.tatsu.data.AppDatabase
import org.rionlabs.tatsu.ui.screen.main.stats.StatsViewModel
import org.rionlabs.tatsu.ui.screen.main.timer.TimerViewModel

object ViewModelModule {

    fun get() = module {
        viewModel {
            TimerViewModel(
                timerController = get(),
                settingManager = get(),
                silentModeManager = get(),
                vibrationsManager = get()
            )
        }

        viewModel {
            StatsViewModel(
                timerDao = get<AppDatabase>().timerDao()
            )
        }
    }
}