package id.bagusbayu.duelfasttap.repo.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import id.bagusbayu.duelfasttap.model.DataHistory

@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(dataHistory: DataHistory)

    @Delete
    suspend fun delete(dataHistory: DataHistory)

    @Update
    suspend fun update(dataHistory: DataHistory)

    @Query("select * from DataHistory")
    fun getAllSaved() : LiveData<List<DataHistory>>

    @Query("select * from DataHistory")
    fun getAllData() : Cursor

}