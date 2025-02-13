package com.afif.test.dealjavatechnicaltest.ui.splash

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.afif.test.dealjavatechnicaltest.databinding.ActivitySplashBinding
import com.afif.test.dealjavatechnicaltest.ui.home.MainActivity
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieEventListener
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.lottiefiles.dotlottie.core.widget.DotLottieAnimation
import kotlinx.coroutines.MainScope

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lottieSetup()
    }

    private fun lottieSetup() {
        val splashAnimation = DotLottieAnimation(this)
        val lottieConfig = Config.Builder()
            .autoplay(true)
            .speed(1f)
            .loop(false) // Play once
            .source(DotLottieSource.Asset("lottie_splash.lottie")) // Load your gift opening animation
            .build()
        splashAnimation.load(lottieConfig)

        //  a new dialog to show the animation
        val animationDialog = Dialog(this)
        animationDialog.setContentView(splashAnimation)
        animationDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        animationDialog.setCancelable(false) // Prevent dismissing the dialog
        animationDialog.show()

        // Use a Handler to delay the transition to IngredientOpenActivity
        Handler(Looper.getMainLooper()).postDelayed({
            animationDialog.dismiss() // Dismiss the animation dialog
            splashAnimation.destroy()
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, 4000)
    }
}