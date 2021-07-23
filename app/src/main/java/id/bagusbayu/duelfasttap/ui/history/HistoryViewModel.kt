package id.bagusbayu.duelfasttap.ui.history

import android.app.Activity
import android.app.Application
import androidx.lifecycle.*
import id.bagusbayu.duelfasttap.model.DataHistory
import id.bagusbayu.duelfasttap.repo.local.HistoryService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {

    // Generate Page number
    private val _page = MutableLiveData<Int>()
    val page : LiveData<Int> get() = _page

    fun setPage(page : Int) = viewModelScope.launch { _page.value = page }

    // variabel database
    private val _history = MutableLiveData<List<DataHistory>>()
    val history : LiveData<List<DataHistory>> get() = _history

    // Get data from database
    fun getSavedData(application: Application, activity: Activity) = viewModelScope.launch {
        HistoryService(application).getAllSaved().collect {
            it.observe(activity as LifecycleOwner){ history ->
                _history.value = history
            }
        }
    }

    // Delete data from database
    fun removeData(application: Application, dataHistory: DataHistory) = viewModelScope.launch {
        HistoryService(application).removeFromSave(dataHistory)
    }

}