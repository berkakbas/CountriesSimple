package com.example.countriessimple.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriessimple.model.Country
import com.example.countriessimple.service.CountryAPI
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _countryError = MutableLiveData<Boolean>()
    val countryError: LiveData<Boolean> = _countryError

    private val _countryLoading = MutableLiveData<Boolean>()
    val countryLoading: LiveData<Boolean> = _countryLoading

    val countryApi = CountryAPI.retrofitService

    fun getDataFromAPI() {
        _countryLoading.value = true
        viewModelScope.launch {
            try {
                val countryList = countryApi.getPhotos()
                _countries.value = countryList
                _countryError.value = false
            } catch (e: Exception) {
                _countryError.value = true
            }
        }
        _countryLoading.value = false
    }
}