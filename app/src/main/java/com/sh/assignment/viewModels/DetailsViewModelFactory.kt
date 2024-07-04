package com.sh.assignment.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sh.assignment.repo.ApiService

class DetailsViewModelFactory(
    private val repo: ApiService,
    private val id: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(repo, id) as T
    }

}