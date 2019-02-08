package org.rionlabs.tatsu.ui.screen.main.timer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.rionlabs.tatsu.data.AppDatabase
import org.rionlabs.tatsu.data.model.Timer
import org.rionlabs.tatsu.data.model.TimerState

class TimerViewModel(val app: Application) : AndroidViewModel(app) {

    private val mTimerData: MutableLiveData<Timer> = MutableLiveData()
    val timerData: LiveData<Timer>
        get() = mTimerData

    init {
        viewModelScope.launch {
            async(Dispatchers.IO) {
                val list = AppDatabase.getInstance(app.applicationContext)
                    .timerDao()
                    .getAll()

                if (list.size < 0) {
                    val timer = list[0]
                    mTimerData.value = timer
                    if (timer.state == TimerState.RUNNING) {
                        startTimer()
                    }
                }
            }
        }
    }

    fun startTimer() {

    }

    fun stopTimer() {

    }

    fun cancelTimer() {

    }

    override fun onCleared() {
        viewModelScope.async {
            val timer = mTimerData.value
            timer?.let {
                AppDatabase.getInstance(app.applicationContext)
                    .timerDao()
                    .insert(it)
            }
        }
    }
}
