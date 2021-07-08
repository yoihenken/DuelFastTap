package id.bagusbayu.duelfasttap.ui.dashboard.newgamedialog

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.DialogFragment
import id.bagusbayu.duelfasttap.R
import id.bagusbayu.duelfasttap.databinding.DialogNewGameBinding
import id.bagusbayu.duelfasttap.ui.mode1.Mode1Activity

class NewGameDialogFragment() : DialogFragment() {

    private lateinit var binding: DialogNewGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogNewGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set layout
        dialog?.window?.apply {
            setBackgroundDrawableResource(R.drawable.bg_border_2)
            val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
            setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        binding.apply {
            // Set Player Toggle Button
            tbPlayer.addOnButtonCheckedListener { group, checkedId, isChecked ->

                when (checkedId) {
                    group[0].id -> selectedButtonPlayer()
                    group[1].id -> selectedButtonPlayer()
                    group[2].id -> selectedButtonPlayer()
                    else -> selectedButtonPlayer()
                }
            }

            //Set Mode Toggle Button
            tbModeGame.addOnButtonCheckedListener { group, checkedId, isChecked ->
                when (checkedId) {
                    group[0].id -> selectedButtonMode()
                    group[1].id -> selectedButtonMode()
                    else -> selectedButtonMode()
                }
            }
            tvHintMode.text = generateHint(selectedMode())
            btnStartGame.setOnClickListener { startModeGame(selectedMode()) }
        }
    }

    // Generate selected Mode
    private fun selectedMode() = with(binding) {
        return@with when ("${tbPlayer.checkedButtonId} ${tbModeGame.checkedButtonId}") {
            "${tbPlayer[0].id} ${tbModeGame[0].id}" -> 0
            "${tbPlayer[0].id} ${tbModeGame[1].id}" -> 1
            "${tbPlayer[1].id} ${tbModeGame[0].id}" -> 2
            "${tbPlayer[1].id} ${tbModeGame[1].id}" -> 3
            "${tbPlayer[2].id} ${tbModeGame[0].id}" -> 4
            "${tbPlayer[2].id} ${tbModeGame[1].id}" -> 5
            else -> -1
        }
    }

    // Change Button PLAYER UI
    private fun selectedButtonPlayer() = with(binding) {

        tvHintMode.text = generateHint(selectedMode())

        when (tbPlayer.checkedButtonId) {
            tbPlayer[0].id -> {
                tbt2Player.apply {
                    setBackgroundColor(resources.getColor(R.color.purple_200))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.white)))
                }
                tbt3Player.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.purple_200)))
                }
                tbt4Player.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.purple_200)))
                }
            }
            tbPlayer[1].id -> {
                tbt2Player.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.purple_200)))
                }
                tbt3Player.apply {
                    setBackgroundColor(resources.getColor(R.color.purple_200))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.white)))
                }
                tbt4Player.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.purple_200)))
                }
            }
            tbPlayer[2].id -> {
                tbt2Player.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.purple_200)))
                }
                tbt3Player.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.purple_200)))
                }
                tbt4Player.apply {
                    setBackgroundColor(resources.getColor(R.color.purple_200))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.white)))
                }
            }
            else -> {
                tbt2Player.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.purple_200)))
                }
                tbt3Player.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.purple_200)))
                }
                tbt4Player.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.purple_200)))
                }
            }
        }
    }

    // Change Button GAME MODE UI
    private fun selectedButtonMode() = with(binding) {

        tvHintMode.text = generateHint(selectedMode())

        when (tbModeGame.checkedButtonId) {
            tbModeGame[0].id -> {
                tbtTimeMode.apply {
                    setBackgroundColor(resources.getColor(R.color.player_2))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.white)))
                }
                tbt50Mode.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.player_1)))
                }
            }
            tbModeGame[1].id -> {
                tbtTimeMode.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.player_2)))
                }
                tbt50Mode.apply {
                    setBackgroundColor(resources.getColor(R.color.player_1))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.white)))
                }
            }
            else -> {
                tbtTimeMode.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.player_2)))
                }
                tbt50Mode.apply {
                    setBackgroundColor(resources.getColor(R.color.white))
                    iconTint = ColorStateList.valueOf((resources.getColor(R.color.player_1)))
                }
            }
        }
    }

    //Generate string for hint
    private fun generateHint(mode: Int) =
        when (mode) {
            0 -> getString(R.string.hint_2_player_time)
            1 -> getString(R.string.hint_2_player_50)
            2 -> getString(R.string.hint_3_player_time)
            3 -> getString(R.string.hint_3_player_50)
            4 -> getString(R.string.hint_4_player_time)
            5 -> getString(R.string.hint_4_player_50)
            else -> "Select Mode"
        }


    private fun startModeGame(mode: Int) {
        when (mode) {
            0 -> context?.startActivity(
                Intent(
                    context,
                    Mode1Activity::class.java
                )
            )
            1 -> context?.startActivity(
                Intent(
                    context,
                    Mode1Activity::class.java
                )
            )
            2 -> context?.startActivity(
                Intent(
                    context,
                    Mode1Activity::class.java
                )
            )
            3 -> context?.startActivity(
                Intent(
                    context,
                    Mode1Activity::class.java
                )
            )
            4 -> context?.startActivity(
                Intent(
                    context,
                    Mode1Activity::class.java
                )
            )
            5 -> context?.startActivity(
                Intent(
                    context,
                    Mode1Activity::class.java
                )
            )
            else -> "Select Mode"
        }
    }

}