package com.example.countriessimple.service

import com.example.countriessimple.model.Country
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CountryAPIService {
    @GET("berkakbas/CountryDataSet/master/countrydataset.json")
    suspend fun getPhotos(): List<Country>
}

object CountryAPI {
    val retrofitService: CountryAPIService by lazy { retrofit.create(CountryAPIService::class.java) }
}