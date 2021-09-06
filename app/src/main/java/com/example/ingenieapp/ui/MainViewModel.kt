package com.example.ingenieapp.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ingenieapp.common.Event
import com.example.ingenieapp.model.CharacterModel
import com.example.ingenieapp.network.APIStatus
import com.example.ingenieapp.network.BreakingBadApi
import kotlinx.coroutines.*
import javax.inject.Inject

internal class MainViewModel @Inject constructor(private val apiService: BreakingBadApi) :
    ViewModel() {

    private val _charItems = MutableLiveData<List<CharacterModel>>()
    val charItems: LiveData<List<CharacterModel>>
        get() = _charItems

    private val _status = MutableLiveData<APIStatus>()
    val status: LiveData<APIStatus>
        get() = _status

    private val _selectedCharItem = MutableLiveData<CharacterModel>()
    val selectedCharItem: LiveData<CharacterModel>
        get() = _selectedCharItem

    private val _navigateToDetails = MutableLiveData<Event<CharacterModel>>()
    val navigateToDetails: LiveData<Event<CharacterModel>>
        get() = _navigateToDetails

    init {

        getBreakingBadCharacters()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun getBreakingBadCharacters() {

        viewModelScope.launch {
            try {
                _status.value = APIStatus.LOADING
                val apiResponse = apiService.getCharacters()

                _charItems.value = apiResponse
                _status.value = APIStatus.DONE
            } catch (e: Exception) {
                _status.value = APIStatus.ERROR
                _charItems.value = ArrayList()
            }
        }
    }

    fun userSelectsItem(item: CharacterModel) {
        _navigateToDetails.value =
            Event(item)
        _selectedCharItem.value = item
    }

}