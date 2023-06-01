package com.example.countriessimple.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.countriessimple.model.Country
import com.example.countriessimple.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val _country = MutableLiveData<Country>()
    val country: LiveData<Country> = _country

    fun getCountryFromDB(uuid: Int) {
        viewModelScope.launch {
            val selectedCountry = CountryDatabase(getApplication()).countryDao().getCountry(uuid)
            _country.value = selectedCountry
        }
    }
}