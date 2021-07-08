package id.bagusbayu.duelfasttap.ui.dashboard.aboutdialog

import android.app.Activity
import android.app.AlertDialog
import android.content.res.ColorStateList
import android.util.Log
import id.bagusbayu.duelfasttap.R
import id.bagusbayu.duelfasttap.databinding.DialogAboutBinding
import id.bagusbayu.duelfasttap.databinding.DialogNewGameBinding

class AboutDialog (val activity: Activity) {

    private lateinit var aboutDialog: AlertDialog
    private lateinit var binding : DialogAboutBinding

    fun startDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        binding = DialogAboutBinding.inflate(inflater)
        binding.btnClose.setOnClickListener { dismissDialog() }
        aboutDialog = builder.create()
        aboutDialog.setView(binding.root, 0, 0, 0, 0)
        aboutDialog.window?.setBackgroundDrawableResource(R.drawable.bg_border_2)
        aboutDialog.show()
    }

    fun dismissDialog(){
        aboutDialog.dismiss()
    }

}