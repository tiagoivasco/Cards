package com.ivasco.cards.data.model

import com.google.gson.annotations.SerializedName

data class Return(
    @SerializedName("patch") var patch: String? = null,
    @SerializedName("classes") var classes: ArrayList<String> = arrayListOf(),
    @SerializedName("sets") var sets: ArrayList<String> = arrayListOf(),
    @SerializedName("standard") var standard: ArrayList<String> = arrayListOf(),
    @SerializedName("wild") var wild: ArrayList<String> = arrayListOf(),
    @SerializedName("types") var types: ArrayList<String> = arrayListOf(),
    @SerializedName("factions") var factions: ArrayList<String> = arrayListOf(),
    @SerializedName("qualities") var qualities: ArrayList<String> = arrayListOf(),
    @SerializedName("races") var races: ArrayList<String> = arrayListOf(),
    @SerializedName("locales") var locales: Locales? = Locales()
)

data class Locales(
    @SerializedName("DE_DE") var DEDE: String? = null,
    @SerializedName("EN_GB") var ENGB: String? = null,
    @SerializedName("EN_US") var ENUS: String? = null,
    @SerializedName("ES_ES") var ESES: String? = null,
    @SerializedName("ES_MX") var ESMX: String? = null,
    @SerializedName("FR_FR") var FRFR: String? = null,
    @SerializedName("IT_IT") var ITIT: String? = null,
    @SerializedName("KO_KR") var KOKR: String? = null,
    @SerializedName("PL_PL") var PLPL: String? = null,
    @SerializedName("PT_BR") var PTBR: String? = null,
    @SerializedName("RU_RU") var RURU: String? = null,
    @SerializedName("ZH_CN") var ZHCN: String? = null,
    @SerializedName("ZH_TW") var ZHTW: String? = null,
    @SerializedName("JA_JP") var JAJP: String? = null,
    @SerializedName("TH_TH") var THTH: String? = null
)