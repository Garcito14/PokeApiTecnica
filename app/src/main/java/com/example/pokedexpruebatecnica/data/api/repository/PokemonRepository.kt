package com.example.pokedexpruebatecnica.data.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedexpruebatecnica.data.api.model.PokemonResponse
import com.example.pokedexpruebatecnica.data.api.network.PokeDexApi

class PokemonRepository(private  val api: PokeDexApi) {

        private val pokemonLiveData = MutableLiveData<PokemonResponse>()

    val pokemons : LiveData<PokemonResponse>
    get() = pokemonLiveData

//    suspend fun getPokemon(limit:Int , offset:Int): Response<PokemonResponse> {
//        val response : Response<PokemonResponse>  = api.getPokemon(limit,offset)
//        return response
//    }

    suspend fun getPokemon(limit:Int,offset:Int){
        val result = api.getPokemon(1013,0)
        if (result.body() != null){
            pokemonLiveData.postValue(result.body())
        }
    }
}