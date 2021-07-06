package id.bagusbayu.duelfasttap.tools

import android.view.Gravity

object Helper {

    // Prepare game for 3s
    const val countDownPreparing = 4 * 1000L

    // Start game for 3s
    const val countDownGame = 11 * 1000L

    // Total targaet 50 tap
    const val totalGame = 50

    val mode = listOf(
        Gravity.START or Gravity.TOP,
        Gravity.START or Gravity.CENTER,
        Gravity.START or Gravity.BOTTOM,
        Gravity.END or Gravity.TOP,
        Gravity.END or Gravity.CENTER,
        Gravity.END or Gravity.BOTTOM,
        Gravity.CENTER or Gravity.TOP,
        Gravity.CENTER or Gravity.CENTER,
        Gravity.CENTER or Gravity.BOTTOM,
    )
}