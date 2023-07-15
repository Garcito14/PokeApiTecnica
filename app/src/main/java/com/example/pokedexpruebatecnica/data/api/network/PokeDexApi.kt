package com.example.pokedexpruebatecnica.data.api.network

import com.example.pokedexpruebatecnica.data.api.ApiConstants.POKE_DETAIL
import com.example.pokedexpruebatecnica.data.api.ApiConstants.POKE_LIST
import com.example.pokedexpruebatecnica.data.api.model.PokemonModels
import com.example.pokedexpruebatecnica.data.api.model.PokemonResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeDexApi {
    @GET(POKE_LIST)
    suspend fun getPokemon(@Query("limit")limit:Int, @Query("offset") offset:Int): Response<PokemonResponse>

    @GET(POKE_DETAIL)
    suspend fun getPokemonDetail(@Path("name") id:String) : Response<PokemonModels>

}