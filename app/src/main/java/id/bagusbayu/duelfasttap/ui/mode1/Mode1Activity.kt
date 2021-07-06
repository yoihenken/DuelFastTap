package id.bagusbayu.duelfasttap.ui.mode1

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import id.bagusbayu.duelfasttap.R
import id.bagusbayu.duelfasttap.databinding.ActivityMode1Binding
import id.bagusbayu.duelfasttap.tools.Helper
import kotlin.random.Random


class Mode1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMode1Binding
    private val model: Mode1ViewModel by viewModels()
    private lateinit var countdownTimer: CountDownTimer
    private val mode = Helper.mode
    private var preparing = true
    private var reset = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMode1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide the nav bar and status bar
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

        preparingGame(preparing)

        binding.apply {

//            Player 1 Target
            btnPlayer1.apply {
                setOnClickListener {
                    // Positioning Target
                    layoutParams = positioning()

                    //Add point
                    model.addPlayer1Count()
                    model.player1Count.observe(this@Mode1Activity) {
                        Log.d("Mode1Activity", "it\t: $it")
                        tvPlayer1Count.text = it.toString()
                    }
                }
            }

//            Player 2 Target
            btnPlayer2.apply {
                setOnClickListener {
                    // Positioning Target
                    layoutParams = positioning()

                    //Add point
                    model.apply {
                        addPlayer2Count()
                        player2Count.observe(this@Mode1Activity) {
                            tvPlayer2Count.text = it.toString()
                        }
                    }
                }
            }

            btnBackHome.setOnClickListener { finish() } //end game
            btnResetGame.setOnClickListener { resetGame() } //reset game
        }

    }

    // Prepare game state
    private fun preparingGame(preparing: Boolean) {
        binding.apply {

            if (preparing) {
                startTimer(Helper.countDownPreparing)
                btnPlayer1.hide()
                btnPlayer2.hide()
            } else {
                startTimer(Helper.countDownGame)
                btnPlayer1.show()
                btnPlayer2.show()
            }
        }
    }

    private fun startTimer(timeInSeconds: Long) {

        if (reset) {
            countdownTimer.cancel()
            reset = false
        }

        countdownTimer = object : CountDownTimer(timeInSeconds, 1000) {

            // Time finish
            override fun onFinish() {
                when (preparing) {
                    true -> {
                        preparing = false
                        preparingGame(preparing)
                    }
                    else -> {
                        resultGame()
                    }
                }
            }

            // Counting Time
            override fun onTick(timeMilliSeconds: Long) {
                val seconds = (timeMilliSeconds / 1000) % 60
                Log.d("Mode1Activity", "seconds\t: $seconds")

                binding.apply {
                    if (seconds <= 3) tvGameTimeCount.setTextColor(resources.getColor(R.color.player_1))
                    else tvGameTimeCount.setTextColor(resources.getColor(R.color.black))
                    tvGameTimeCount.text = "${seconds}S"
                }
            }

        }
        countdownTimer.start()
    }

    // Change target position
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

    // show result game state
    private fun resultGame() {
        binding.apply {

            tvResultPlayer1.apply {
                visibility = View.VISIBLE
                text = when (model.playerWinner()) {
                    0 -> getString(R.string.draw)
                    1 -> getString(R.string.winner)
                    2 -> getString(R.string.loser)
                    else -> getString(R.string.draw)
                }
            }
            tvResultPlayer2.apply {
                visibility = View.VISIBLE
                text = when (model.playerWinner()) {
                    0 -> getString(R.string.draw)
                    1 -> getString(R.string.loser)
                    2 -> getString(R.string.winner)
                    else -> getString(R.string.draw)
                }
            }

            btnPlayer1.hide()
            btnPlayer2.hide()
        }
    }

    // Reset game from 0 with same mode
    private fun resetGame(){
        reset = true
        model.resetGame()
        preparing = true
        preparingGame(preparing)

        binding.apply {
            tvResultPlayer1.visibility = View.INVISIBLE
            tvResultPlayer2.visibility = View.INVISIBLE
        }
    }

    // Hide the nav bar and status bar when status bar displayed
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