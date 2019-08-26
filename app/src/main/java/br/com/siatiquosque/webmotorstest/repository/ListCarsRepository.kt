package br.com.siatiquosque.webmotorstest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import br.com.siatiquosque.webmotorstest.api.WebMotorsService
import br.com.siatiquosque.webmotorstest.db.CarDao
import br.com.siatiquosque.webmotorstest.util.Listing
import br.com.siatiquosque.webmotorstest.util.NetworkState
import br.com.siatiquosque.webmotorstest.vo.Car
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListCarsRepository @Inject constructor(
    private val carDao: CarDao,
    private val webMotorsService: WebMotorsService
) {

    fun loadCars(
        page: Int = 1,
        compositeDisposable: CompositeDisposable
    ): Listing<Car> {
        val networkState = MutableLiveData<NetworkState>()
        return Listing(

            carDao.loadCarByPage()
                .toLiveData(
                    pageSize = 10,
                    boundaryCallback = WebMotorsBoundaryCallback(
                        webMotorsService,
                        carDao,
                        page,
                        networkState,
                        compositeDisposable
                    )
                ),
            networkState
        )


    }

    fun refreshCars(): Completable {
        return Completable.fromRunnable { carDao.deleteAll() }
    }
}