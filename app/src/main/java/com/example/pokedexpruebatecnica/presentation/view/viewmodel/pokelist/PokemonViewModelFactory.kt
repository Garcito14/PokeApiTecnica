package com.example.pokedexpruebatecnica.presentation.view.viewmodel.pokelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexpruebatecnica.data.api.repository.PokemonRepository

class PokemonViewModelFactory(private val pokemonRepository: PokemonRepository):ViewModelProvider.Factory {
    override fun <T :ViewModel> create (modelClass: Class<T>):T{
        return PokemonListViewModel(pokemonRepository) as T
    }

}