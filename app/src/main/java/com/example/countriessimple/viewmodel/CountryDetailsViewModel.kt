package com.example.countriessimple.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countriessimple.model.Country

class CountryDetailsViewModel {
    private val _country = MutableLiveData<Country>()
    val country: LiveData<Country> = _country
}