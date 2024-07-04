package com.sh.assignment.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sh.assignment.models.Result
import com.sh.assignment.repo.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailsViewModel (
    private val api : ApiService,
    id : Int
) : ViewModel(){

    val character : LiveData<Result>
        get() = api.character

    init {
        viewModelScope.launch(Dispatchers.IO) {
            api.getCharacter(id)
        }
    }

}