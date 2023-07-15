package com.example.pokedexpruebatecnica.core

import com.example.pokedexpruebatecnica.data.api.ApiConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokedexRetrofitHelper {
        fun getRetrofit():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
