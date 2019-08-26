package br.com.siatiquosque.webmotorstest.di

import br.com.siatiquosque.webmotorstest.MainActivity
import br.com.siatiquosque.webmotorstest.cars.CarsFragment
import br.com.siatiquosque.webmotorstest.cars.CarsModule
import br.com.siatiquosque.webmotorstest.details.DetailsFragment
import br.com.siatiquosque.webmotorstest.details.DetailsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule{

    @ContributesAndroidInjector(modules = [CarsModule::class])
    abstract fun carsFragment(): CarsFragment

    @ContributesAndroidInjector(modules = [DetailsModule::class])
    abstract fun detailsFragment(): DetailsFragment
}