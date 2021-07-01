package com.example.gotennaasssesment.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import networking.NetworkState
import repo.CrimeRepo

class MainViewModel() : ViewModel() {

    private val repository = CrimeRepo()

    val networkState = repository.networkState

    val crimes = repository.crimes

    fun getCrimes() {
        repository.getCrimes()
    }

    val showLoadingIndicator: LiveData<Boolean> = Transformations.map(networkState) {
        it == NetworkState.LOADING
    }

    val showRecyclerView: LiveData<Boolean> = Transformations.map(networkState) {
        it == NetworkState.SUCCESS && (crimes.value ?: emptyList()).isNotEmpty()
    }

    val showEmptyState: LiveData<Boolean> = Transformations.map(networkState) {
        it == NetworkState.SUCCESS && (crimes.value ?: emptyList()).isEmpty()
    }

    val showErrorState: LiveData<Boolean> = Transformations.map(networkState) {
        it == NetworkState.FAILURE
    }

}
