package model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import networking.OutcomeStatus

class CrimeResponse(

    @SerializedName("category")
    var category: String? = "",

    @SerializedName("persistent_id")
    var persistentId: String? = "",

    @SerializedName("location_subtype")
    var locationSubtype: String? = "",

    @SerializedName("id")
    var id: String? = "",

    @SerializedName("location")
    var location: String? = "",

    @SerializedName("context")
    var context: String? = "",

    @SerializedName("month")
    var month: String? = "",

    @SerializedName("location_type")
    var locationType: String? = "",

    @SerializedName("outcome_status")
    var outcomeStatus: OutcomeStatus? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(OutcomeStatus::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(persistentId)
        parcel.writeString(locationSubtype)
        parcel.writeString(id)
        parcel.writeString(location)
        parcel.writeString(context)
        parcel.writeString(month)
        parcel.writeString(locationType)
        parcel.writeParcelable(outcomeStatus, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CrimeResponse> {
        override fun createFromParcel(parcel: Parcel): CrimeResponse {
            return CrimeResponse(parcel)
        }

        override fun newArray(size: Int): Array<CrimeResponse?> {
            return arrayOfNulls(size)
        }
    }
}