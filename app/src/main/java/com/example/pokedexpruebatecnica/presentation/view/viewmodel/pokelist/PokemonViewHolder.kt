package com.example.pokedexpruebatecnica.presentation.view.viewmodel.pokelist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexpruebatecnica.data.api.model.PokeResult
import com.example.pokedexpruebatecnica.data.api.model.PokemonModels
import com.example.pokedexpruebatecnica.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)
    fun bind(
        pokemonItemResponse: PokeResult,
        onItemSelected: (String) -> Unit
    ) {
        binding.tvPokemonName.text =" ${position + 1} -  ${pokemonItemResponse.name} "
        binding.root.setOnClickListener { onItemSelected(pokemonItemResponse.name) }

    }
}