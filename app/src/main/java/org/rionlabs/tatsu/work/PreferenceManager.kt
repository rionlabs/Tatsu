package org.rionlabs.tatsu.work

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import org.rionlabs.tatsu.data.Converters.Companion.toString
import org.rionlabs.tatsu.data.Converters.Companion.toTimerType
import org.rionlabs.tatsu.data.model.TimerType

class PreferenceManager(val app: Application) {

    private val preferences =
        app.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setTimerType(timerType: TimerType) {
        preferences.edit {
            putString(PREF_KEY_TIMER_TYPE, toString(timerType))
        }
    }

    fun getTimerType(): TimerType {
        val defaultTimerType = toString(TimerType.WORK)
        val timerTypeString = preferences.getString(PREF_KEY_TIMER_TYPE, defaultTimerType)
            ?: defaultTimerType
        return toTimerType(timerTypeString)
    }

    fun shouldShowGuide(): Boolean {
        return preferences.getBoolean(PREF_KEY_GUIDE_SHOWN, true)
    }

    fun setGuideShown() {
        preferences.edit {
            putBoolean(PREF_KEY_GUIDE_SHOWN, false)
        }
    }

    companion object {
        private const val PREF_NAME = "private_preferences"

        private const val PREF_KEY_TIMER_TYPE = "pref_timer_type"
        private const val PREF_KEY_GUIDE_SHOWN = "pref_guide_shown"
    }
}