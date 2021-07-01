package networking

import model.CrimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CrimesAPI {
    @GET("/api/crimes-no-location?category=all-crime&force=leicestershire")
    fun getCrimes(): Call<List<CrimeResponse>>

    //with options
    @GET("/api/crimes-no-location?category={category}}&force={force}}&date={date}")
    fun getCrimes(@Path("category") category: String, @Path("force") force: String, @Path("date") date: String): Call<List<CrimeResponse>>
}