package com.glsebastiany.heroessample.core.repository.model

import android.os.Parcel
import android.os.Parcelable

data class CharactersResponse (
        val status: String,
        val data: Data
) {

    data class Data(
            val offset: Int,
            val limit: Int,
            val total: Int,
            val count: Int,
            val results: List<CharacterResponse>
    ) {
        data class CharacterResponse(
                val id: Int,
                val name: String,
                val description: String
        ) : Parcelable {
            constructor(parcel: Parcel) : this(
                    parcel.readInt(),
                    parcel.readString(),
                    parcel.readString()) {
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeString(description)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<CharacterResponse> {
                override fun createFromParcel(parcel: Parcel): CharacterResponse {
                    return CharacterResponse(parcel)
                }

                override fun newArray(size: Int): Array<CharacterResponse?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}