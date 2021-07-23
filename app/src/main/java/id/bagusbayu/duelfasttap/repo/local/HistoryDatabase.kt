package id.bagusbayu.duelfasttap.repo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.bagusbayu.duelfasttap.model.DataHistory

@Database(entities = [DataHistory::class], version = 1, exportSchema = false)

abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao() : HistoryDao

    companion object{
        private var instance : HistoryDatabase ?= null

        @Synchronized
        fun getInstance(context: Context) : HistoryDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext, HistoryDatabase::class.java, "database_history_ftb"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            return instance!!
        }

        fun getInstanceMainThread(context: Context): HistoryDatabase {
            return Room.databaseBuilder(
                context.applicationContext, HistoryDatabase::class.java, "database_history_ftb"
            )
                .allowMainThreadQueries()
                .build()
        }

    }



}