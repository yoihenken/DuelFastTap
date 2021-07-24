package id.bagusbayu.duelfasttap.mode1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.bagusbayu.duelfasttap.ui.mode1.Mode1ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
 * create test for :
 * get test count player 1
 * get test count player 2
 * get test winner with count 50
 * get test winner with have many count
 */

class Mode1ViewModelTest {
    private lateinit var viewModel: Mode1ViewModel
    val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = Mode1ViewModel()
    }

    //    test count player 1
    @Test
    fun addPlayer1Count() {
        viewModel.addPlayer1Count()

        val data = viewModel.player1Count.value
        println(data)
        //Should not null
        assertNotNull(data)
        //Should be count 1
        assertEquals(1, data)
    }

    //    test count player 2
    @Test
    fun addPlayer2Count() {
        viewModel.addPlayer2Count()
        val data = viewModel.player2Count.value
        //Should not null
        assertNotNull(data)
        //Should be count 1
        assertEquals(1, data)
    }

    //    Test for count until 50 for player 4, and check is the winner player 3
    @Test
    fun win50countPlayer1() {
        for (i in 1..50) {
            viewModel.addPlayer1Count()
        }
        val data = viewModel.player1Count.value
        //Should be 50
        assertEquals(50, data)

        val winner = viewModel.stateFifty.value
        //The winner should player 1 (1)
        assertEquals(1, winner)
    }

    //    Test for a winner that have many count
    @Test
    fun winByCount() {
        viewModel.addPlayer1Count()
        viewModel.addPlayer2Count()
        viewModel.addPlayer2Count()

        val winner = viewModel.playerWinner()

        //Should not be null
        assertNotNull(winner)

        //Should be Player 2
        assertEquals(2, winner)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}