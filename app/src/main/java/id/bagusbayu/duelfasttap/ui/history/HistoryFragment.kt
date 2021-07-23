package id.bagusbayu.duelfasttap.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.bagusbayu.duelfasttap.databinding.FragmentHistoryBinding
import id.bagusbayu.duelfasttap.model.DataHistory
import id.bagusbayu.duelfasttap.tools.Helper.toDataBasedPlayerMode

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val model: HistoryViewModel by viewModels()
    private var curPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.setPage(arguments?.getInt(PAGER_NUMBER) ?: 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.apply {
            page.observe(viewLifecycleOwner) {
                curPage = it
                getSavedData(requireActivity().application, requireActivity())
                history.observe(viewLifecycleOwner) {
                    recycleData(it, curPage)
                }
            }
        }
    }

    private fun recycleData(data: List<DataHistory>, page: Int) {

        val dataBasedPlayer = data.toDataBasedPlayerMode(page)

        binding.tvNoData.apply {
            if (dataBasedPlayer.isEmpty()) visibility = View.VISIBLE
            else visibility = View.INVISIBLE
        }

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = HistoryAdapter(context, dataBasedPlayer, page)
            Log.d("HistoryFragment", "recycleData Size based Player: ${dataBasedPlayer.size} ")
            Log.d("HistoryFragment", "recycleData Size: ${data.size} ")
            Log.d("HistoryFragment", "recycleData: $data ")
        }
    }

    companion object {
        private const val PAGER_NUMBER = "pager_number"

        @JvmStatic
        fun newInstance(position: Int) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(PAGER_NUMBER, position)
                }
            }
    }
}