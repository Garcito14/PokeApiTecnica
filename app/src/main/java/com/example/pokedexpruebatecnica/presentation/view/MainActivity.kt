package com.example.pokedexpruebatecnica.presentation.view


import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexpruebatecnica.core.PokedexRetrofitHelper

import com.example.pokedexpruebatecnica.data.api.repository.PokemonRepository
import com.example.pokedexpruebatecnica.data.api.network.PokeDexApi

import com.example.pokedexpruebatecnica.databinding.ActivityMainBinding
import com.example.pokedexpruebatecnica.presentation.view.DetailActivity.Companion.EXTRA_ID
import com.example.pokedexpruebatecnica.presentation.view.viewmodel.pokelist.PokemonListAdapter

import com.example.pokedexpruebatecnica.presentation.view.viewmodel.pokelist.PokemonListViewModel
import com.example.pokedexpruebatecnica.presentation.view.viewmodel.pokelist.PokemonViewModelFactory


class MainActivity : AppCompatActivity() {
    private  var isLoading : Boolean = true

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonListAdapter: PokemonListAdapter
   val pokeDexApi = PokedexRetrofitHelper.getRetrofit().create(PokeDexApi::class.java)
    val pokemonRepository = PokemonRepository(pokeDexApi)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, PokemonViewModelFactory(pokemonRepository)).get(PokemonListViewModel::class.java)

        binding.svPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // SearchView para buscar pokemon  deseado y dejarlo primero en la lista
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.svPokemon.setOnSearchClickListener(){
                    mostrarDatos()
                }
                pokemonListAdapter.filter.filter(query)
                Log.d("BUSCADOR ", query.toString())
                binding.svPokemon.setQuery("",false)
                binding.svPokemon.onActionViewCollapsed()
               return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        initUI()
    }



    private fun initUI() {

        //codigo generico para mostrar el reloj digital
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val digitalFont=Typeface.createFromAsset(applicationContext.assets,"font/digital.TTF")
            binding.pokemonTC.typeface= digitalFont


             mostrarDatos()

            pokemonListAdapter = PokemonListAdapter { navigateToDetail(it) }
            binding.pokemonRV.layoutManager = LinearLayoutManager(this)
            binding.pokemonRV.adapter = pokemonListAdapter
    }
    // aqui sew hace la llamada  para mostrar la lista de pokemon en el recycler view
    private fun mostrarDatos(){
        viewModel.pokemon.observe(this, Observer { list ->
            binding.pokemonRV.adapter = pokemonListAdapter
            pokemonListAdapter.updateList(list.results)
            if(!list.results.isEmpty())
            {
                isLoading = false
            }
            binding.pbPokemon.setVisibility(View.GONE)
            Log.d(
                "POKEMON",
                "RESPONSE  :  ${pokemonListAdapter.updateList(list.results).toString()}"
            )
        })
    }
    // el intent para ir a la ventana de detalle
    private fun navigateToDetail(id:String){
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(EXTRA_ID,id)

        startActivity(intent)
    }
}

