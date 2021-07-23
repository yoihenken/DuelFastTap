package id.bagusbayu.duelfasttap.tools

import android.view.Gravity
import id.bagusbayu.duelfasttap.model.DataHistory

object Helper {

    // Prepare game for 3s
    const val countDownPreparing = 4 * 1000L

    // Start game for 3s
    const val countDownGame = 11 * 1000L

    // Total targaet 50 tap
    const val totalGame = 50

    // Position for 2 Player
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

    //Position for 3 Player and 4 Player
    val verticalBias = listOf(
        0.05,
        0.15,
        0.25,
        0.35,
        0.65,
        0.75,
        0.85,
        0.95
    )

    val horizontalBias = listOf(
        0.1,
        0.3,
        0.5,
        0.7,
        0.9
    )

    fun List<DataHistory>.toDataBasedPlayerMode(page : Int): MutableList<DataHistory> {
        val data = mutableListOf<DataHistory>()
        when (page) {
            0 -> { // 2 Player
                this.forEach {
                    if (it.modePlayer == "2 PLAYER") data.add(it)
                }
            }
            1 -> { // 3 Player
                this.forEach {
                    if (it.modePlayer == "3 PLAYER") data.add(it)
                }
            }
            2 -> { // 4 Player
                this.forEach {
                    if (it.modePlayer == "4 PLAYER") data.add(it)
                }
            }
        }
        return data
    }

}