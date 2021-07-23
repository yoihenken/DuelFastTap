package id.bagusbayu.duelfasttap.repo.local

import android.app.Application
import id.bagusbayu.duelfasttap.model.DataHistory
import kotlinx.coroutines.flow.flow

class HistoryService (application: Application) {

    private var historyDao : HistoryDao
    private val database = HistoryDatabase.getInstance(application)
    private val databaseMain = HistoryDatabase.getInstanceMainThread(application)


    init {
        historyDao = database.historyDao()
    }

    suspend fun addToSave(dataHistory: DataHistory){
        historyDao.insert(dataHistory)
    }

    suspend fun removeFromSave(dataHistory: DataHistory){
        historyDao.delete(dataHistory)
    }

    suspend fun getAllSaved() = flow {
        emit(historyDao.getAllSaved())
    }
}