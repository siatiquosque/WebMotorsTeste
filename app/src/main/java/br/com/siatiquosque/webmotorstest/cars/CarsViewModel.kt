package br.com.siatiquosque.webmotorstest.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.siatiquosque.webmotorstest.repository.ListCarsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CarsViewModel @Inject constructor(val listCarsRepository: ListCarsRepository) : ViewModel() {

    fun invalidationDataSource() {
        listCarsRepository.refreshCars()
            .subscribeOn(Schedulers.newThread())
            .subscribe({ carsResult.value?.dataSource?.invalidate() }, {})
            .apply { compositeDisposable.addAll(this) }
    }

    private val compositeDisposable = CompositeDisposable()
    private val listingResult = listCarsRepository.loadCars(compositeDisposable = compositeDisposable)
    val carsResult = listingResult.pagedList
    val networkState = listingResult.networkState

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}