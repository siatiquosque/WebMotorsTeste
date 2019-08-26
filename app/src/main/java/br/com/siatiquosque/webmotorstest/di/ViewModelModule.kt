package br.com.siatiquosque.webmotorstest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.siatiquosque.webmotorstest.cars.CarsViewModel
import br.com.siatiquosque.webmotorstest.details.DetailsViewModel
import br.com.siatiquosque.webmotorstest.util.WebMotorsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CarsViewModel::class)
    abstract fun bindCarsViewModel(carsViewModel: CarsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(carsViewModel: DetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: WebMotorsViewModelFactory): ViewModelProvider.Factory

}