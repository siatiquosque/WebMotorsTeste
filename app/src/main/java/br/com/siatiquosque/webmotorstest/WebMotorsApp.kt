package br.com.siatiquosque.webmotorstest

import android.app.Application
import androidx.multidex.MultiDexApplication
import br.com.siatiquosque.webmotorstest.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class WebMotorsApp : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun androidInjector() = androidInjector

}