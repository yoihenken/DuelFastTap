package id.bagusbayu.duelfasttap.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.bagusbayu.duelfasttap.databinding.ActivitySplashScreenBinding
import id.bagusbayu.duelfasttap.ui.mode1.Mode1Activity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    private val delay = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, Mode1Activity::class.java))
            finish()
        }, delay)
    }
}