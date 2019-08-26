package br.com.siatiquosque.webmotorstest.api

import androidx.lifecycle.LiveData
import br.com.siatiquosque.webmotorstest.vo.Car
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebMotorsService {
    @GET("OnlineChallenge/Vehicles")
    fun getCars(@Query("Page") page: Int): Observable<List<Car>>

}