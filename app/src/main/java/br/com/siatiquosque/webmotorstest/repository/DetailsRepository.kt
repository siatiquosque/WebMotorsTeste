package br.com.siatiquosque.webmotorstest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.siatiquosque.webmotorstest.db.CarDao
import br.com.siatiquosque.webmotorstest.util.NetworkState
import br.com.siatiquosque.webmotorstest.vo.Car
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val carDao: CarDao
) {

    fun loadDetail(id: Int): LiveData<Car> {
        val networkState = MutableLiveData<NetworkState>()

        return carDao.getCar(id)

    }
}