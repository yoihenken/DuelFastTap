package id.bagusbayu.duelfasttap.ui.mode1

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import id.bagusbayu.duelfasttap.R
import id.bagusbayu.duelfasttap.databinding.ActivityMode1Binding
import id.bagusbayu.duelfasttap.model.DataHistory
import id.bagusbayu.duelfasttap.tools.Helper
import kotlin.random.Random


class Mode1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMode1Binding
    private val model: Mode1ViewModel by viewModels()
    private lateinit var countdownTimer: CountDownTimer
    private val mode = Helper.mode
    private var preparing = true
    private var reset = false
    private var gameMode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMode1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide the nav bar and status bar
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

        gameMode = intent.getIntExtra(EXTRA_MODE, 0)
        preparingGame(preparing, gameMode)

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

            if (gameMode == 1) startFifty() //For trigger win when gameMode is fifty

            btnBackHome.setOnClickListener { finish() } //end game
            btnResetGame.setOnClickListener { resetGame() } //reset game
        }

    }

    // Prepare game state
    private fun preparingGame(preparing: Boolean, gameMode: Int) {
        binding.apply {

            if (preparing) {
                startTimer(Helper.countDownPreparing)
                btnPlayer1.hide()
                btnPlayer2.hide()
            } else {

                when (gameMode) {
//                    Mode Time
                    0 -> {
                        startTimer(Helper.countDownGame)
                        btnPlayer1.show()
                        btnPlayer2.show()
                    }
//                    Mode 50
                    1 -> {
                        tvGameTime.text = getString(R.string.total)
                        tvGameTimeCount.text = "/50"
                        tvGameTimeCount.setTextColor(funOnSecondaryColor())
                        btnPlayer1.show()
                        btnPlayer2.show()
                    }
                }
            }
        }
    }

    private fun startFifty() {
        model.stateFifty.observe(this@Mode1Activity) {
            Log.d("Mode1Activity", "stateFifty\t: $it")
            when (it) {
                1 -> resultGame()
                2 -> resultGame()
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
                        preparingGame(preparing, gameMode)
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
                    else tvGameTimeCount.setTextColor(funOnSecondaryColor())
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

            // Save to database
            model.saveHistory(
                application, DataHistory(
                    modePlayer = "2 PLAYER",
                    modeGame = gameMode,
                    winner = when (model.playerWinner()) {
                        0 -> getString(R.string.draw)
                        1 -> getString(R.string.player_1)
                        2 -> getString(R.string.player_2)
                        else -> getString(R.string.draw)
                    },
                    scorePlayer1 = tvPlayer1Count.text.toString().toInt(),
                    scorePlayer2 = tvPlayer2Count.text.toString().toInt(),
                )
            )
        }
    }

    // Reset game from 0 with same mode
    private fun resetGame() {
        reset = true
        model.resetGame()
        preparing = true
        preparingGame(preparing, gameMode)

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

    private fun funOnSecondaryColor(): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(R.attr.colorOnSecondary, typedValue, true)
        return typedValue.data
    }

    companion object {
        const val EXTRA_MODE = "extra_mode"
    }
}