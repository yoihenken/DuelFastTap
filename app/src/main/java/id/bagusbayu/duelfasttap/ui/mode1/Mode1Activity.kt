package id.bagusbayu.duelfasttap.ui.mode1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import id.bagusbayu.duelfasttap.databinding.ActivityMode1Binding
import id.bagusbayu.duelfasttap.tools.Helper
import kotlin.random.Random


class Mode1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMode1Binding
    private val model : Mode1ViewModel by viewModels()
    private val mode = Helper.mode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMode1Binding.inflate(layoutInflater)
        setContentView(binding.root)


        // Hide the nav bar and status bar
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)


        binding.apply {

//            Player 1
            btnPlayer1.apply {
                setOnClickListener {
                    // generate random place
                    val random = Random.nextInt(9)

                    // Positioning Target
                    layoutParams = positioning()

                    //Add point
                    model.addPlayer1Count()
                    model.player1Count.observe(this@Mode1Activity){
                        Log.d("Mode1Activity", "it\t: $it")
                        tvPlayer1Count.text = it.toString()
                    }

                }
            }

//            Player 2
            btnPlayer2.apply {
                setOnClickListener {
                    // generate random place
                    val random = Random.nextInt(9)

                    // Positioning Target
                    layoutParams = positioning()

                    //Add point
                    model.apply {
                        addPlayer2Count()
                        player2Count.observe(this@Mode1Activity){
                            tvPlayer2Count.text = it.toString()
                        }
                    }
                }
            }
        }

    }

    private fun positioning(): CoordinatorLayout.LayoutParams {
        // generate random place
        val random = Random.nextInt(9)

        // Positioning Target
        val position = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.WRAP_CONTENT,
            CoordinatorLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = mode[random]
            setMargins(80, 100, 80, 100)
        }
        return position
    }


    // Hide the nav bar and status bar when statusbar displayed
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    }
}