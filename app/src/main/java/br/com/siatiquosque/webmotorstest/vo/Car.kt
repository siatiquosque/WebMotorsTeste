package br.com.siatiquosque.webmotorstest.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true)

    @SerializedName("ID")
    val id: Int,

    @SerializedName("Make")
    val make: String,

    @SerializedName("Model")
    val model: String,

    @SerializedName("Version")
    val version: String,

    @SerializedName("Image")
    val image: String,

    @SerializedName("KM")
    val km: Int,

    @SerializedName("Price")
    val price: String,

    @SerializedName("YearModel")
    val yearModel: Int,

    @SerializedName("YearFab")
    val yearFab: Int,

    @SerializedName("Color")
    val color: String,

    var page: Int?
)
