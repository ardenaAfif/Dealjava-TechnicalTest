package com.afif.test.dealjavatechnicaltest.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.afif.test.dealjavatechnicaltest.MainActivity
import com.afif.test.dealjavatechnicaltest.databinding.ActivitySplashBinding
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieEventListener
import com.lottiefiles.dotlottie.core.util.DotLottieSource

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = Config.Builder()
            .autoplay(true)
            .speed(1f)
//            .loop(true)
            .source(DotLottieSource.Asset("lottie_splash.lottie")) // asset from the asset folder .json or .lottie
            .useFrameInterpolation(true)
            .playMode(Mode.FORWARD)
            .build()
        binding.lottieSplash.load(config)
        binding.lottieSplash.addEventListener(eventListener)
    }

    private val eventListener = object : DotLottieEventListener {
        override fun onPlay() {
        }

        override fun onDestroy() {
            super.onDestroy()
        }

        override fun onPause() {
        }

        override fun onStop() {
        }

        override fun onFrame(frame: Float) {
        }

        override fun onLoop(loopCount: Int) {
        }

        override fun onComplete() {
//            Toast.makeText(this@SplashActivity, "Animation completed", Toast.LENGTH_SHORT).show()
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }, 200)
        finish()
    }
//    private fun lottieSetup() {
//        val config = Config.Builder()
//            .autoplay(true)
//            .speed(1f)
//            .loop(true)
//            .source(DotLottieSource.Asset("lottie_splash.lottie")) // asset from the asset folder .json or .lottie
//            .useFrameInterpolation(true)
//            .playMode(Mode.FORWARD)
//            .build()
//        binding.lottieSplash.load(config)
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            navigateToMainActivity()
//        }, 5000)
//    }


}