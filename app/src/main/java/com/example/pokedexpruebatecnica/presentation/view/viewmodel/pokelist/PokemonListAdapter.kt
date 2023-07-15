package com.example.pokedexpruebatecnica.presentation.view.viewmodel.pokelist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexpruebatecnica.R
import com.example.pokedexpruebatecnica.data.api.model.PokeResult

class PokemonListAdapter(var pokemkonList: List<PokeResult> = emptyList() , private val onItemSelected:(String) -> Unit) :
    RecyclerView.Adapter<PokemonViewHolder>(), Filterable {

    private var listaInicial: List<PokeResult> = pokemkonList.toList()
    fun updateList(pokemkonList: List<PokeResult>) {
        this.pokemkonList = pokemkonList
        notifyDataSetChanged()
    }
    fun resetToInitialState() {
        pokemkonList = listaInicial
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemkonList[position]
        holder.bind(item, onItemSelected)
    }

    override fun getItemCount() = pokemkonList.size

     override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString()
                val filteredList = mutableListOf<PokeResult>()
                for (item in pokemkonList) {
                    if (item.name.toLowerCase().contains(query) || filteredList.isNotEmpty()) {
                        filteredList.add(item.copy(item.name))
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                pokemkonList = results?.values as List<PokeResult>
                notifyDataSetChanged()
            }
        }
    }


}