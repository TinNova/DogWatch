package tin.novakovic.dogwatch.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dog(
    val breed: String,
    val subBreed: String?
) : Parcelable