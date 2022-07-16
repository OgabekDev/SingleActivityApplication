package dev.ogabek.android_saa.fragment.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import dev.ogabek.android_saa.R
import dev.ogabek.android_saa.extension.activityNavController
import dev.ogabek.android_saa.extension.navigateSafely
import dev.ogabek.android_saa.manager.AuthManager

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countDownTimer()
    }

    private fun countDownTimer() {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (AuthManager.isAuthorized) {
                    activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
                } else {
                    activityNavController().navigateSafely(R.id.action_global_loginFlowFragment)
                }
            }
        }.start()
    }
}