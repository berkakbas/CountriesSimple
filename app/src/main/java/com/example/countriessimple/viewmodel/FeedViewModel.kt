package com.example.countriessimple.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.countriessimple.model.Country
import com.example.countriessimple.service.CountryAPI
import com.example.countriessimple.service.CountryDatabase
import kotlinx.coroutines.launch


class FeedViewModel(application: Application) : AndroidViewModel(application) {
    private val UPDATE_TIME = "update_time"
    private val sharedPref = application.getSharedPreferences("time", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()
    private var refreshTime = 0.1 * 60 * 1000 * 1000 * 1000L //5 mins

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _countryError = MutableLiveData<Boolean>()
    val countryError: LiveData<Boolean> = _countryError

    private val _countryLoading = MutableLiveData<Boolean>()
    val countryLoading: LiveData<Boolean> = _countryLoading

    val countryApi = CountryAPI.retrofitService

    fun refreshData() {
        val lastUpdateTime = sharedPref.getLong(UPDATE_TIME, 0)

        if (System.nanoTime() - lastUpdateTime > refreshTime) {
            getDataFromAPI()
        } else {
            getDataFromSQLite()
        }
    }

    fun getDataFromAPI() {
        _countryLoading.value = true
        viewModelScope.launch {
            try {
                val countryList = countryApi.getPhotos()
                storeInSQLite(countryList)
                _countries.value = countryList
                _countryError.value = false
            } catch (e: Exception) {
                _countryError.value = true
            }
        }
        _countryLoading.value = false
    }

    private fun getDataFromSQLite() {
        _countryLoading.value = true
        viewModelScope.launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
        }
    }

    private fun storeInSQLite(list: List<Country>) {
        viewModelScope.launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray()) // -> list -> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i++
            }
            showCountries(list)
        }
        editor.putLong(UPDATE_TIME, System.nanoTime())
        editor.commit()
    }

    private fun showCountries(countryList: List<Country>) {
        _countries.value = countryList
        _countryError.value = false
        _countryLoading.value = false
    }
}