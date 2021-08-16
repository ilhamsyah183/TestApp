package com.dicoding.myapplication

import android.os.Parcel
import android.os.Parcelable

//data class People (
//    val photo: Int,
//    val name: String ?,
//    val username: String?,
//    val company: String?,
//    val location: String?,
//    val follower: String?,
//    val following: String?,
//    val repository: String?
//): Parcelable{
//    constructor(parcel: Parcel): this(
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString()
//    )
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(photo)
//        parcel.writeString(name)
//        parcel.writeString(username)
//        parcel.writeString(company)
//        parcel.writeString(location)
//        parcel.writeString(follower)
//        parcel.writeString(following)
//        parcel.writeString(repository)
//    }
//
//    companion object CREATOR: Parcelable.Creator<People>{
//        override fun createFromParcel(parcel: Parcel): People {
//            return People(parcel)
//        }
//
//        override fun newArray(size: Int): Array<People?> {
//            return arrayOfNulls(size)
//        }
//
//    }
//}