package com.example.pokedexpruebatecnica.presentation.view

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.pokedexpruebatecnica.core.PokedexRetrofitHelper.getRetrofit
import com.example.pokedexpruebatecnica.data.api.model.PokemonModels
import com.example.pokedexpruebatecnica.data.api.network.PokeDexApi
import com.example.pokedexpruebatecnica.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
    }
    private  var isShiny :Boolean  = false

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()

       getPokemonDetail(id)
        binding.pbDetail.isVisible

    }


    private fun createUI(pokemonModels: PokemonModels) {
        binding.pbDetail.isVisible
        //se utiliza glide para "bindear" el sprite del pokemon a la imageView
       Glide.with(this).load(pokemonModels.sprites.frontDefault).into(binding.ivPokemon)
        isShiny(pokemonModels)
        prepareState(pokemonModels)
        binding.tvHeight.text = "Altura: ${pokemonModels.height / 10.0}m"
        binding.tvWeight.text = "Peso: ${pokemonModels.weight / 10.0}kg"



    }

    // seteo de los correspondientes atributos para luego ser mostrados en el detalle
    private fun prepareState(pokemonModels: PokemonModels) {

        updateHeight(binding.viewHP,pokemonModels.stats[0].baseStat)
        updateHeight(binding.viewAttack,pokemonModels.stats[1].baseStat)
        updateHeight(binding.viewDefense,pokemonModels.stats[2].baseStat)
        updateHeight(binding.viewSPA,pokemonModels.stats[3].baseStat)
        updateHeight(binding.viewSPD,pokemonModels.stats[4].baseStat)
       updateHeight(binding.viewSpeed,pokemonModels.stats[5].baseStat)

    }

    //esto sirve para  hacer que las view (barras de estadisticas) tomen el tamaño segun el valor de la estadistica de cada pokemon
    private fun updateHeight(view: View, stat:Int){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }
    private fun pxToDp(px:Float):Int {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,resources.displayMetrics).roundToInt()
    }

    //esto sirve para hacer la llamada para obtener el detalle de los pokemon
    private fun getPokemonDetail(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val pokemonDetail =
                getRetrofit().create(PokeDexApi::class.java).getPokemonDetail(id)
            if (pokemonDetail.body() != null ) {
                runOnUiThread {
                    binding.pbDetail.setVisibility(View.GONE)
                    createUI(pokemonDetail.body()!!)
                }
                pokemonDetail.body()
            }
        }
    }

        //funcion que hace que al apretar el boton "shiny" cambie la imagen del pokemon a su forma shiny como tambien al apretarlo de nuevo, vuelve  a su forma original
    fun isShiny(pokemonModels: PokemonModels){
        binding.btnShiny.setOnClickListener {
            isShiny = !isShiny
            if(isShiny){
                Glide.with(this).load(pokemonModels.sprites.frontShiny).into(binding.ivPokemon)
            }
            else {
                Glide.with(this).load(pokemonModels.sprites.frontDefault).into(binding.ivPokemon)
            }
            binding.btnShiny.text = if(!isShiny) "Shiny" else "Default"
        }
    }


}

