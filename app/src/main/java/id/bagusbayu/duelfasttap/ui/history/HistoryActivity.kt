package id.bagusbayu.duelfasttap.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import id.bagusbayu.duelfasttap.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            vpHistory.adapter = PagerAdapter(this@HistoryActivity)
            TabLayoutMediator(tabHistory, vpHistory){ tabHistory, position ->
                 tabHistory.text = when(position) {
                     0 -> "2 PLAYER"
                     1 -> "3 PLAYER"
                     else -> "4 PLAYER"
                 }
            }.attach()
            btnBack.setOnClickListener { finish() }
        }

    }

    private inner class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment = HistoryFragment.newInstance(position)
    }
}