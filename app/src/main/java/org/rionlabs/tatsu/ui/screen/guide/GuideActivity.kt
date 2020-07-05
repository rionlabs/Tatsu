package org.rionlabs.tatsu.ui.screen.guide

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.github.appintro.model.SliderPage
import org.koin.android.ext.android.get
import org.rionlabs.tatsu.R
import org.rionlabs.tatsu.ui.screen.main.MainActivity
import org.rionlabs.tatsu.work.PreferenceManager

class GuideActivity : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isSkipButtonEnabled = false

        val defaultSliderPage = SliderPage(
            backgroundColor = ContextCompat.getColor(this, R.color.color_primary),
            descriptionColor = ColorUtils.setAlphaComponent(
                ContextCompat.getColor(this, R.color.color_on_primary), 100
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                defaultSliderPage.copy(
                    title = "Simple Pomodoro Timer",
                    description = "Manage your with simple timer. Set work repetitions, Take short breaks!",
                    imageDrawable = R.drawable.undraw_time_management
                )
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                defaultSliderPage.copy(
                    title = "Keep track of Productivity",
                    description = "Track your productive time & break times with simple statistics!",
                    imageDrawable = R.drawable.undraw_dev_productivity
                )
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                defaultSliderPage.copy(
                    title = "Keep the Distractions Away",
                    description = "Turn on Silent Mode to mute all notifications when you are working!",
                    imageDrawable = R.drawable.undraw_project_completed
                )
            )
        )

        setTransformer(
            AppIntroPageTransformerType.Parallax(
                titleParallaxFactor = -1.0,
                imageParallaxFactor = 100.0,
                descriptionParallaxFactor = -1.0
            )
        )
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Set flag
        val preferenceManager = get<PreferenceManager>()
        preferenceManager.setGuideShown()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}