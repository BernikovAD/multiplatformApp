package com.example.example

import com.google.gson.annotations.SerializedName


data class Fees(

    @SerializedName("world") var world: World? = World(),
    @SerializedName("russia") var russia: Russia? = Russia(),
    @SerializedName("usa") var usa: Usa? = Usa()

)