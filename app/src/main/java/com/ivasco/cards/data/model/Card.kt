package com.ivasco.cards.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mechanic(
    var name: String? = null
) : Parcelable

@Parcelize
data class Card(
    var cardId: String? = null,
    var dbfId: Int = 0,
    var name: String? = null,
    var cardSet: String? = null,
    var type: String? = null,
    var health: Int = 0,
    var text: String? = null,
    var artist: String? = null,
    var playerClass: String? = null,
    var locale: String? = null,
    var mechanics: ArrayList<Mechanic>? = null,
    var faction: String? = null,
    var rarity: String? = null,
    var collectible: Boolean = false,
    var img: String? = null,
    var classes: ArrayList<String>? = null,
    var elite: Boolean = false,
    var flavor: String? = null,
    var cost: Int = 0,
    var spellSchool: String? = null,
    var attack: Int = 0,
    var imgGold: String? = null,
    var howToGet: String? = null,
    var howToGetGold: String? = null,
    var multiClassGroup: String? = null,
    var durability: Int = 0,
    var race: String? = null,
    var armor: String? = null
) : Parcelable