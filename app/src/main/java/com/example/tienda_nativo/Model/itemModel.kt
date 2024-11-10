package com.example.tienda_nativo.Model

import android.media.Rating
import android.os.Parcel
import android.os.Parcelable

data class itemModel(
    var title: String= "",
    var description:String="",
    var picUrl: ArrayList <String> = ArrayList(),
    var size :ArrayList<String> = ArrayList(),
    var price:Double=0.0,
    var rating: Double=0.0,
    var numberInCart: Int=0

): Parcelable{
    constructor(parcel:Parcel):this(
            parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readDouble()
            )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeStringList(picUrl)
        dest.writeStringList(size)
        dest.writeDouble(price)
        dest.writeDouble(rating)
    }

    companion object CREATOR : Parcelable.Creator<itemModel> {
        override fun createFromParcel(parcel: Parcel): itemModel {
            return itemModel(parcel)
        }

        override fun newArray(size: Int): Array<itemModel?> {
            return arrayOfNulls(size)
        }
    }
}

