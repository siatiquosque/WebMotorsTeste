package br.com.siatiquosque.webmotorstest.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import br.com.siatiquosque.webmotorstest.api.WebMotorsService
import br.com.siatiquosque.webmotorstest.db.CarDao
import br.com.siatiquosque.webmotorstest.util.NetworkState
import br.com.siatiquosque.webmotorstest.vo.Car
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WebMotorsBoundaryCallback(
    private val webMotorsService: WebMotorsService,
    private val carDao: CarDao,
    private var pageNumber: Int,
    private val networkState: MutableLiveData<NetworkState>,
    private val compositeDisposable: CompositeDisposable

) : PagedList.BoundaryCallback<Car>() {

    override fun onZeroItemsLoaded() {
        pageNumber = 1
        fetchAndStoreCar()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Car) {
        pageNumber++
        fetchAndStoreCar()
    }

    private fun fetchAndStoreCar() {
        networkState.value = NetworkState.LOADING
        compositeDisposable.add(
            webMotorsService.getCars(pageNumber)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .doOnNext { carDao.insertCars(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    networkState.value = NetworkState.LOADED
                }, { t: Throwable ->

                })
        )
    }
}

