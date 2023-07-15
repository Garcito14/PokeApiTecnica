package com.example.pokedexpruebatecnica.presentation.view.viewmodel.pokelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpruebatecnica.data.api.repository.PokemonRepository
import com.example.pokedexpruebatecnica.data.api.model.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {

        pokemonRepository.getPokemon(1013,0)
        }
    }

    val pokemon : LiveData<PokemonResponse>
    get()  = pokemonRepository.pokemons





}