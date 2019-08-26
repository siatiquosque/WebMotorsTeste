package br.com.siatiquosque.webmotorstest.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import br.com.siatiquosque.webmotorstest.vo.Car


@Dao
interface CarDao {

    @Query("SELECT * FROM car ORDER BY id")
    fun loadCarByPage(): DataSource.Factory<Int, Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCars(cars: List<Car>)

    @Query("SELECT COUNT(*) FROM car")
    fun getAllItems(): Int

    @Query("SELECT * FROM car WHERE id = :id ")
    fun getCar(id: Int): LiveData<Car>

    @Delete
    fun deleteCars(cars: List<Car>)

    @Query("DELETE FROM car")
    fun deleteAll()
}