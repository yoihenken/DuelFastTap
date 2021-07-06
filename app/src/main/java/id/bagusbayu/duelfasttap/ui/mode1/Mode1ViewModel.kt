package id.bagusbayu.duelfasttap.ui.mode1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Mode1ViewModel : ViewModel() {

    // Player 1 Count
    private val _player1Count = MutableLiveData<Int>()
    val player1Count : LiveData<Int> get() = _player1Count

    private var currentPlayer1Count = 0

    fun addPlayer1Count (){
        currentPlayer1Count++
        _player1Count.value = currentPlayer1Count
    }

    // Player 2 Count
    private val _player2Count = MutableLiveData<Int>()
    val player2Count : LiveData<Int> get() = _player2Count

    private var currentPlayer2Count = 0

    fun addPlayer2Count (){
        currentPlayer2Count++
        _player2Count.value = currentPlayer2Count
    }

}