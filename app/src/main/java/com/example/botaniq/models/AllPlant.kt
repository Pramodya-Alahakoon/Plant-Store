package com.example.botaniq.models

import android.os.Parcel
import android.os.Parcelable

data class AllPlant(
    val name: String,
    val price: String,
    val imageResource: Int,
    val description: String,
    val rating: Int,
    val isPopular: Boolean,
    val type: String,
    val color: String,
    val potType: String,
    var isFavorite: Boolean,
    val careLevel: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeInt(imageResource)
        parcel.writeString(description)
        parcel.writeInt(rating)
        parcel.writeByte(if (isPopular) 1 else 0)
        parcel.writeString(type)
        parcel.writeString(color)
        parcel.writeString(potType)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeString(careLevel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AllPlant> {
        override fun createFromParcel(parcel: Parcel): AllPlant {
            return AllPlant(parcel)
        }

        override fun newArray(size: Int): Array<AllPlant?> {
            return arrayOfNulls(size)
        }
    }
}