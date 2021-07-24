package id.bagusbayu.duelfasttap.mode2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.bagusbayu.duelfasttap.ui.mode2.Mode2ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

/*
 * create test for :
 * get test count player 1
 * get test count player 2
 * get test count player 3
 * get test winner with count 50
 * get test winner with have many count
 */

class Mode2ViewModelTest {
    private lateinit var viewModel: Mode2ViewModel
    val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = Mode2ViewModel()
    }

    //    test count player 1
    @Test
    fun addPlayer1Count() {
        viewModel.addPlayer1Count()

        val data = viewModel.player1Count.value
        println(data)
        //Should not null
        Assert.assertNotNull(data)
        //Should be count 1
        Assert.assertEquals(1, data)
    }

    //    test count player 2
    @Test
    fun addPlayer2Count() {
        viewModel.addPlayer2Count()
        val data = viewModel.player2Count.value
        //Should not null
        Assert.assertNotNull(data)
        //Should be count 1
        Assert.assertEquals(1, data)
    }

    //    test count player 3
    @Test
    fun addPlayer3Count() {
        viewModel.addPlayer3Count()
        val data = viewModel.player3Count.value
        //Should not null
        Assert.assertNotNull(data)
        //Should be count 1
        Assert.assertEquals(1, data)
    }

    //    Test for count until 50 for player 4, and check is the winner player 3
    @Test
    fun win50countPlayer3() {
        for (i in 1..50) {
            viewModel.addPlayer3Count()
        }
        val data = viewModel.player3Count.value
        //Should be 50
        Assert.assertEquals(50, data)

        val winner = viewModel.stateFifty.value
        //The winner should player 1 (1)
        Assert.assertEquals(3, winner)
    }

    //    Test for a winner that have many count
    @Test
    fun winByCount() {
        viewModel.addPlayer1Count()
        viewModel.addPlayer2Count()
        viewModel.addPlayer2Count()
        viewModel.addPlayer3Count()

        val winner = viewModel.playerWinner()

        //Should not be null
        Assert.assertNotNull(winner)

        //Should be Player 2
        Assert.assertEquals(2, winner)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}