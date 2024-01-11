package com.john.firstmenubasic.dao

import com.john.firstmenubasic.interfaceDao.DaoUserInterface
import com.john.firstmenubasic.models.User
import com.john.firstmenubasic.object_model.UserRepository


class DaoUser private constructor(): DaoUserInterface {
    companion object {
        val myDao: DaoUser by lazy{ //lazy delega a un primer acceso
            DaoUser() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos

    override fun getDataUser(): List<User> = UserRepository.listUser

    fun addUser(user: User) {
        UserRepository.listUser.add(user)
    }
}

