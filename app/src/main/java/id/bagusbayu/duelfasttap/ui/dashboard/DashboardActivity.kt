package id.bagusbayu.duelfasttap.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import id.bagusbayu.duelfasttap.R
import id.bagusbayu.duelfasttap.databinding.ActivityDashboardBinding
import id.bagusbayu.duelfasttap.databinding.ActivitySplashScreenBinding
import id.bagusbayu.duelfasttap.ui.mode1.Mode1Activity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            bgBackDash.startAnimation(AnimationUtils.loadAnimation(this@DashboardActivity, R.anim.rotate_animation))

            btnNewGame.setOnClickListener {
                startActivity(Intent(this@DashboardActivity, Mode1Activity::class.java))
            }
        }

    }
}