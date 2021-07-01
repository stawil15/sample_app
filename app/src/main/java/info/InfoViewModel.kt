package info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.CrimeResponse

class InfoViewModel() : ViewModel() {
    val crimeData = MutableLiveData<CrimeResponse>()
}