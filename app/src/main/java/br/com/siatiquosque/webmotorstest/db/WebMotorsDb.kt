package br.com.siatiquosque.webmotorstest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.siatiquosque.webmotorstest.vo.Car

@Database(entities = [Car::class], version = 2, exportSchema = false)
abstract class WebMotorsDb : RoomDatabase() {
    abstract fun carDao(): CarDao
}