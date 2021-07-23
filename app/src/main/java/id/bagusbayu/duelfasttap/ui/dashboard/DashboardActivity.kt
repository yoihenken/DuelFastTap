package id.bagusbayu.duelfasttap.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import id.bagusbayu.duelfasttap.R
import id.bagusbayu.duelfasttap.databinding.ActivityDashboardBinding
import id.bagusbayu.duelfasttap.databinding.DialogNewGameBinding
import id.bagusbayu.duelfasttap.ui.dashboard.aboutdialog.AboutDialog
import id.bagusbayu.duelfasttap.ui.dashboard.newgamedialog.NewGameDialogFragment
import id.bagusbayu.duelfasttap.ui.history.HistoryActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private var newGameDialog = NewGameDialogFragment(this)
    private val aboutDialog = AboutDialog(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            bgBackDash.startAnimation(
                AnimationUtils.loadAnimation(
                    this@DashboardActivity,
                    R.anim.rotate_animation
                )
            )

            btnNewGame.setOnClickListener {
                newGameDialog.show(supportFragmentManager, "DialogNewGame")
            }

            btnHistory.setOnClickListener {
                startActivity(Intent(this@DashboardActivity, HistoryActivity::class.java))
            }

            btnAbout.setOnClickListener {
                aboutDialog.startDialog()
            }
        }

    }
}