package com.example.pokedexpruebatecnica.data.api.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(


    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<PokeResult>

)

data class PokeResult (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)


data class PokemonModels(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("is_default") val isDefault: Boolean,
    @SerializedName("order") val order: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("abilities") val abilities: List<Ability>,
    @SerializedName("forms") val forms: List<Form>,
    @SerializedName("game_indices") val gameIndices: List<GameIndex>,
    @SerializedName("held_items") val heldItems: List<HeldItem>,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String,
    @SerializedName("moves") val moves: List<Move>,
    @SerializedName("species") val species: Species,
    @SerializedName("stats") val stats: List<Stat>,
    @SerializedName("types") val types: List<Type>,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,
// added property for the Spanish flavor text entries
    var spanishFlavorTextEntries: List<String> = emptyList()
)

data class Species(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class Sprites(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)