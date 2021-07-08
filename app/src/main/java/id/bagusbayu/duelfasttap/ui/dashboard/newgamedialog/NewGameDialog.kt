package id.bagusbayu.duelfasttap.ui.dashboard.newgamedialog

import android.app.Activity
import android.app.AlertDialog
import android.content.res.ColorStateList
import android.util.Log
import id.bagusbayu.duelfasttap.R
import id.bagusbayu.duelfasttap.databinding.DialogNewGameBinding

class NewGameDialog (val activity: Activity) {

    private lateinit var newGameDialog: AlertDialog
    private lateinit var binding : DialogNewGameBinding

    fun chooseMode(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        binding = DialogNewGameBinding.inflate(inflater)

        binding.apply {
            tbPlayer.addOnButtonCheckedListener { group, checkedId, isChecked ->
                when(checkedId){
                    0 -> {
                        tbt2Player.apply {
                            setBackgroundColor(activity.resources.getColor(R.color.purple_500))
                            iconTint = ColorStateList.valueOf((activity.resources.getColor(R.color.white)))
                            Log.d("DashAct", "2 Player clicked")
                        }
                    }
                }
            }
        }

        newGameDialog = builder.create()
        newGameDialog.setView(binding.root, 0, 0, 0, 0)
        newGameDialog.show()


    }

    fun dismissDialog(){
        newGameDialog.dismiss()
    }

}