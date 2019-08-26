package br.com.siatiquosque.webmotorstest.cars

import br.com.siatiquosque.webmotorstest.api.WebMotorsService
import br.com.siatiquosque.webmotorstest.db.CarDao
import br.com.siatiquosque.webmotorstest.db.WebMotorsDb
import br.com.siatiquosque.webmotorstest.repository.ListCarsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CarsModule {

   @Provides
    fun provideListCarRepository(
        carDao: CarDao,
        webMotorsService: WebMotorsService
    ): ListCarsRepository {
        return ListCarsRepository(carDao, webMotorsService)
    }

}