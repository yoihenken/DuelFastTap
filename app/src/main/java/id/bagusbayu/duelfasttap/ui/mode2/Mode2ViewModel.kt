package id.bagusbayu.duelfasttap.ui.mode2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class Mode2ViewModel : ViewModel() {

    // Player 1 Count
    private val _player1Count = MutableLiveData<Int>()
    val player1Count: LiveData<Int> get() = _player1Count

    private var currentPlayer1Count = 0

    fun addPlayer1Count() = viewModelScope.launch {
        currentPlayer1Count++
        _player1Count.value = currentPlayer1Count
        if (currentPlayer1Count >= 50) _stateFifty.value = 1
    }

    // Player 2 Count
    private val _player2Count = MutableLiveData<Int>()
    val player2Count: LiveData<Int> get() = _player2Count

    private var currentPlayer2Count = 0

    fun addPlayer2Count() = viewModelScope.launch {
        currentPlayer2Count++
        _player2Count.value = currentPlayer2Count
        if (currentPlayer2Count >= 50) _stateFifty.value = 2
    }

    // Player 3 Count
    private val _player3Count = MutableLiveData<Int>()
    val player3Count: LiveData<Int> get() = _player3Count

    private var currentPlayer3Count = 0

    fun addPlayer3Count() = viewModelScope.launch {
        currentPlayer3Count++
        _player3Count.value = currentPlayer3Count
        if (currentPlayer3Count >= 50) _stateFifty.value = 3
    }

    // Return who player win
    fun playerWinner(): Int = when {
        currentPlayer1Count == currentPlayer2Count && currentPlayer2Count == currentPlayer3Count -> 0
        currentPlayer1Count > currentPlayer2Count && currentPlayer1Count > currentPlayer3Count -> 1
        currentPlayer2Count > currentPlayer1Count && currentPlayer2Count > currentPlayer3Count -> 2
        currentPlayer3Count > currentPlayer1Count && currentPlayer3Count > currentPlayer2Count -> 3
        else -> -1
    }

    // State win first 50
    private val _stateFifty = MutableLiveData<Int>()
    val stateFifty: LiveData<Int> get() = _stateFifty

    fun resetGame(){
        currentPlayer1Count = 0
        _player1Count.value = currentPlayer1Count
        currentPlayer2Count = 0
        _player2Count.value = currentPlayer2Count
        currentPlayer3Count = 0
        _player3Count.value = currentPlayer3Count

        _stateFifty.value = 0
    }
}