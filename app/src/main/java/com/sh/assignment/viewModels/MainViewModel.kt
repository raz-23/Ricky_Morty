package com.sh.assignment.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sh.assignment.models.Characters
import com.sh.assignment.repo.ApiService
import kotlinx.coroutines.Dispatchers import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel (
    private val api : ApiService
) : ViewModel(){

    val characters:LiveData<Characters>
        get() = api.characters

    init {
        viewModelScope.launch(Dispatchers.IO) {
            api.getCharacters(1)
        }
    }
}