package id.bagusbayu.duelfasttap.ui.dashboard

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import id.bagusbayu.duelfasttap.R
import id.bagusbayu.duelfasttap.databinding.ActivityDashboardBinding
import id.bagusbayu.duelfasttap.databinding.ActivitySplashScreenBinding
import id.bagusbayu.duelfasttap.databinding.DialogNewGameBinding
import id.bagusbayu.duelfasttap.ui.dashboard.newgamedialog.NewGameDialog
import id.bagusbayu.duelfasttap.ui.dashboard.newgamedialog.NewGameDialogFragment
import id.bagusbayu.duelfasttap.ui.mode1.Mode1Activity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding
    private lateinit var bindingDialog: DialogNewGameBinding
    private var newGameDialog = NewGameDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingDialog = DialogNewGameBinding.inflate(layoutInflater)

        binding.apply {
            bgBackDash.startAnimation(AnimationUtils.loadAnimation(this@DashboardActivity, R.anim.rotate_animation))

            btnNewGame.setOnClickListener {
                newGameDialog.show(supportFragmentManager,"Dialog")

//                startActivity(Intent(this@DashboardActivity, Mode1Activity::class.java))
            }
        }

    }
}