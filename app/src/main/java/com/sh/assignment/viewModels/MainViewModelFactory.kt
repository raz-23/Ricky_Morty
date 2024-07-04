package com.sh.assignment.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sh.assignment.repo.ApiService

class MainViewModelFactory(private val api:ApiService) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(api) as T
    }
}