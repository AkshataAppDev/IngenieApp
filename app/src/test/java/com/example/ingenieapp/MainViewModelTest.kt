package com.example.ingenieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ingenieapp.common.Event
import com.example.ingenieapp.model.CharacterModel
import com.example.ingenieapp.network.APIStatus
import com.example.ingenieapp.network.BreakingBadApi
import com.example.ingenieapp.ui.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    private val apiService: BreakingBadApi = mock()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(apiService)
    }

    @Test
    fun `when successfully fetch the data from the api, then it should update the value of the status to done`(){
        runBlockingTest {
            whenever(apiService.getCharacters()).thenAnswer { mutableListOf<CharacterModel>() }

            viewModel.getBreakingBadCharacters()

            assertEquals(viewModel.status.value, APIStatus.DONE)
            assertEquals(viewModel.charItems.value is List, true)
        }
    }

    @Test
    fun `on user selects item, it should trigger the event`(){
        val item = mockk<CharacterModel>()
        viewModel.userSelectsItem(item)
        val event = viewModel.navigateToDetails.value
        val selectedItem = viewModel.selectedCharItem.value
        assertEquals(event is Event, true)
        assertEquals(selectedItem, item)
    }

}