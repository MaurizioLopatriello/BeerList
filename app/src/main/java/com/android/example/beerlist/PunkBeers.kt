package com.android.example.beerlist

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PunkBeers(
    val id: Int,
    val name: String,
    val description: String,
    val tagline: String,
    @SerializedName("image_url") val imageURL: String,
    val abv: Float,
    val ibu: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(tagline)
        parcel.writeString(imageURL)
        parcel.writeFloat(abv)
        parcel.writeFloat(ibu)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PunkBeers> {
        override fun createFromParcel(parcel: Parcel): PunkBeers {
            return PunkBeers(parcel)
        }

        override fun newArray(size: Int): Array<PunkBeers?> {
            return arrayOfNulls(size)
        }
    }
}