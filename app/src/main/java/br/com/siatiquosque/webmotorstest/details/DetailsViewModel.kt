package br.com.siatiquosque.webmotorstest.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.siatiquosque.webmotorstest.repository.DetailsRepository
import br.com.siatiquosque.webmotorstest.vo.Car
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getDetails(id:Int) : LiveData<Car> = detailsRepository.loadDetail(id)
}