package com.arttttt.profirumvp.model.user

import android.os.Parcel
import android.os.Parcelable

data class User( val firstName: String, val lastName: String, val photoUrl: String)
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(firstName)
        dest.writeString(lastName)
        dest.writeString(photoUrl)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}