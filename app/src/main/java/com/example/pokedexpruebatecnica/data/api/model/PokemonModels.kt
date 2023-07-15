package com.example.pokedexpruebatecnica.data.api.model

import com.google.gson.annotations.SerializedName




data class FlavorTextEntry(
     @SerializedName("flavor_text") val flavorText: String,
     @SerializedName("language") val language: Language
)

data class Language(
     @SerializedName("name") val name: String,
     @SerializedName("url") val url: String
)

data class Ability(
     @SerializedName("ability") val ability: AbilityInfo,
     @SerializedName("is_hidden") val isHidden: Boolean,
     @SerializedName("slot") val slot: Int
)

data class AbilityInfo(
     @SerializedName("name") val name: String,
     @SerializedName("url") val url: String
)

data class Form(
     @SerializedName("name") val name: String,
     @SerializedName("url") val url: String
)

data class GameIndex(
     @SerializedName("game_index") val gameIndex: Int,
     @SerializedName("version") val version: Version
)

data class Version(
     @SerializedName("name") val name: String,
     @SerializedName("url") val url: String
)

data class HeldItem(
     @SerializedName("item") val item: Item,
     @SerializedName("version_details") val versionDetails: List<VersionDetail>
)

data class Item(
     @SerializedName("name") val name: String,
     @SerializedName("url") val url: String
)

data class VersionDetail(
     @SerializedName("rarity") val rarity: Int,
     @SerializedName("version") val version: Version
)

data class Move(
     @SerializedName("move") val move: MoveInfo,
     @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetail>
)

data class MoveInfo(
     @SerializedName("name") val name: String,
     @SerializedName("url") val url: String
)

data class VersionGroupDetail(
     @SerializedName("level_learned_at") val levelLearnedAt: Int,
     @SerializedName("move_learn_method") val moveLearnMethod: MoveLearnMethod,
     @SerializedName("version_group") val versionGroup: VersionGroup
)

data class MoveLearnMethod(
     @SerializedName("name") val name: String,
     @SerializedName("url") val url: String
)

data class VersionGroup(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)



data class Stat(
     @SerializedName("base_stat") val baseStat: Int,
     @SerializedName("effort") val effort: Int,
     @SerializedName("stat") val stat: StatInfo
)

data class StatInfo(
     @SerializedName("name") val name: String,
     @SerializedName("url") val url: String
)

data class Type(
     @SerializedName("slot") val slot: Int,
     @SerializedName("type") val type: TypeInfo
)

data class TypeInfo(
     @SerializedName("name") val name: String,
     @SerializedName("url") val url: String
)
