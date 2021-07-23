package id.bagusbayu.duelfasttap.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class DataHistory(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,

    @ColumnInfo(name = "modePlayer")
    val modePlayer : String? = null,

    @ColumnInfo(name = "modeGame")
    val modeGame : Int? = null,

    @ColumnInfo(name = "winner")
    val winner : String? = null,

    @ColumnInfo(name = "scorePlayer1")
    val scorePlayer1 : Int? = null,

    @ColumnInfo(name = "scorePlayer2")
    val scorePlayer2 : Int? = null,

    @ColumnInfo(name = "scorePlayer3")
    val scorePlayer3 : Int? = null,

    @ColumnInfo(name = "scorePlayer4")
    val scorePlayer4 : Int? = null

) : Parcelable