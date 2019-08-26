package br.com.siatiquosque.webmotorstest.di

import android.app.Application
import androidx.room.Room
import br.com.siatiquosque.webmotorstest.api.WebMotorsService
import br.com.siatiquosque.webmotorstest.cars.CarsModule
import br.com.siatiquosque.webmotorstest.db.CarDao
import br.com.siatiquosque.webmotorstest.db.WebMotorsDb
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(
    includes = [ViewModelModule::class,
        CarsModule::class]
)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl("http://desafioonline.webmotors.com.br/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDb(application: Application): WebMotorsDb {
        return Room
            .databaseBuilder(application, WebMotorsDb::class.java, "webmotors.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWebMotorsService(retrofit: Retrofit): WebMotorsService {
        return retrofit.create(WebMotorsService::class.java)
    }

    @Singleton
    @Provides
    fun providecarDao(db: WebMotorsDb): CarDao {
        return db.carDao()
    }
}