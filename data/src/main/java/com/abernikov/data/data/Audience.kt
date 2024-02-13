package com.example.example

import com.google.gson.annotations.SerializedName


data class Audience(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("country") var country: String? = null

)