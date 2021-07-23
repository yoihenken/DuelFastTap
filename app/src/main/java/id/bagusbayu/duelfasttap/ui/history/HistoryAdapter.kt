package id.bagusbayu.duelfasttap.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.bagusbayu.duelfasttap.R
import id.bagusbayu.duelfasttap.databinding.ItemsHistoryBinding
import id.bagusbayu.duelfasttap.model.DataHistory

class HistoryAdapter(
    private val context: Context,
    private val dataHistory: List<DataHistory>,
    private val page: Int
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder (view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemsHistoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        binding = ItemsHistoryBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.items_history, parent, false)
        )
        return HistoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        binding.apply {

            val data = dataHistory[position]

            when (page){
                //When Page 2 Player
                0 -> {
                    when(data.modeGame){
                        0 -> ivModeGame.load(R.drawable.ic_time)
                        1 -> ivModeGame.load(R.drawable.ic_fifty)
                    }
                    tvWinnerPlayer.text = data.winner
                    tvScorePlayer1.text = data.scorePlayer1.toString()
                    tvScorePlayer2.text = data.scorePlayer2.toString()

                    tvPlayer3.visibility = View.GONE
                    tvScorePlayer3.visibility = View.GONE
                    tvPlayer4.visibility = View.GONE
                    tvScorePlayer4.visibility = View.GONE
                    split3.visibility = View.GONE
                    split4.visibility = View.GONE
                }
                //When Page 3 Player
                1 -> {
                    when(data.modeGame){
                        0 -> ivModeGame.load(R.drawable.ic_time)
                        1 -> ivModeGame.load(R.drawable.ic_fifty)
                    }
                    tvWinnerPlayer.text = data.winner
                    tvScorePlayer1.text = data.scorePlayer1.toString()
                    tvScorePlayer2.text = data.scorePlayer2.toString()
                    tvScorePlayer3.text = data.scorePlayer3.toString()
                    tvPlayer4.visibility = View.GONE
                    tvScorePlayer4.visibility = View.GONE
                    split4.visibility = View.GONE
                }

                //When Page 4 Player
                2 -> {
                    when(data.modeGame){
                        0 -> ivModeGame.load(R.drawable.ic_time)
                        1 -> ivModeGame.load(R.drawable.ic_fifty)
                    }
                    tvWinnerPlayer.text = data.winner
                    tvScorePlayer1.text = data.scorePlayer1.toString()
                    tvScorePlayer2.text = data.scorePlayer2.toString()
                    tvScorePlayer3.text = data.scorePlayer3.toString()
                    tvScorePlayer4.text = data.scorePlayer4.toString()
                }
            }
        }
    }

    override fun getItemCount(): Int = dataHistory.size


}