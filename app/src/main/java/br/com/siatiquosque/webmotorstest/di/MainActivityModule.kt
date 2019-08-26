package br.com.siatiquosque.webmotorstest.di

import br.com.siatiquosque.webmotorstest.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule{
    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun mainActivity():MainActivity
}