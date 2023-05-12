package com.arqunn.usatoday.data.remote.model

import com.google.gson.annotations.SerializedName

data class SourceDto(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String?
)