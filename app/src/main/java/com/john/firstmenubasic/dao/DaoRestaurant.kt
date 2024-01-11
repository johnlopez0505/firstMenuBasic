package com.john.firstmenubasic.dao

import com.john.firstmenubasic.interfaceDao.InterfaceDao
import com.john.firstmenubasic.models.Restaurant
import com.john.firstmenubasic.object_model.Repository

class DaoRestaurant private constructor(): InterfaceDao {
    companion object {
        val myDao: DaoRestaurant by lazy{ //lazy delega a un primer acceso
            DaoRestaurant() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos
    override
    fun getDataRestaurant(): List<Restaurant> = Repository. listRestauran
}

object DaoHotels2 {
    val myDao by lazy {
        getDataRestaurant()
    }
    private fun getDataRestaurant() : List<Restaurant> = Repository. listRestauran
}