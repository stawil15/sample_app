package networking

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class OutcomeStatus(
    @SerializedName("category")
    var category: String? = "",

    @SerializedName("date")
    var date: String? = ""
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OutcomeStatus> {
        override fun createFromParcel(parcel: Parcel): OutcomeStatus {
            return OutcomeStatus(parcel)
        }

        override fun newArray(size: Int): Array<OutcomeStatus?> {
            return arrayOfNulls(size)
        }
    }
}