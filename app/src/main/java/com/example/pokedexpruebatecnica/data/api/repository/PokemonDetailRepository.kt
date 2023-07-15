package com.example.pokedexpruebatecnica.data.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedexpruebatecnica.data.api.model.PokemonModels
import com.example.pokedexpruebatecnica.data.api.model.PokemonResponse
import com.example.pokedexpruebatecnica.data.api.network.PokeDexApi

class PokemonDetailRepository(private  val api: PokeDexApi) {
    private val pokemonDetailLiveData = MutableLiveData<PokemonModels>()

    val pokemonDetail : LiveData<PokemonModels>
        get() = pokemonDetailLiveData

//    suspend fun getPokemon(limit:Int , offset:Int): Response<PokemonResponse> {
//        val response : Response<PokemonResponse>  = api.getPokemon(limit,offset)
//        return response
//    }

    suspend fun getPokemonDetail(id:String){
        val result = api.getPokemonDetail(id)
        if (result.body() != null){
            pokemonDetailLiveData.postValue(result.body())
        }
    }



}