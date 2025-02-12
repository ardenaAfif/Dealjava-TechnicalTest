package com.afif.test.dealjavatechnicaltest.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.afif.test.dealjavatechnicaltest.databinding.ActivitySplashBinding
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieEventListener
import com.lottiefiles.dotlottie.core.util.DotLottieSource

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var runnable: Runnable? = null

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
        runnable = Runnable {

        }
        Handler().postDelayed({
        }, 200)
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