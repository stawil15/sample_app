package repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import model.CrimeResponse
import networking.CrimesAPI
import networking.NetworkState
import networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CrimeRepo() {
    val networkState = MutableLiveData<NetworkState>()
    val crimes = MutableLiveData<List<CrimeResponse>>()

    val api = RetrofitClient().getInstance()!!.create(CrimesAPI::class.java)

    fun getCrimes(): LiveData<List<CrimeResponse>> {
        networkState.value = NetworkState.LOADING

        api.getCrimes().enqueue(object : Callback<List<CrimeResponse>> {
            override fun onResponse(
                call: Call<List<CrimeResponse>>,
                response: Response<List<CrimeResponse>>
            ) {
                when (response.isSuccessful) {
                    true -> {
                        crimes.value = response.body() ?: emptyList()
                        networkState.value = NetworkState.SUCCESS
                    }
                    false -> networkState.value = NetworkState.FAILURE
                }
            }

            override fun onFailure(call: Call<List<CrimeResponse>>, t: Throwable) {
                networkState.value = NetworkState.FAILURE
            }
        })
        return crimes
    }
}
