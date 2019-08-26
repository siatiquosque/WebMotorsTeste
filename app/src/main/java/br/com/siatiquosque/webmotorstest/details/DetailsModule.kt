package br.com.siatiquosque.webmotorstest.details

import br.com.siatiquosque.webmotorstest.db.CarDao
import br.com.siatiquosque.webmotorstest.repository.DetailsRepository
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {
    @Provides
    fun provideDetailsRepository(carDao: CarDao): DetailsRepository {
       return DetailsRepository(carDao)
    }
}