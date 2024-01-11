package com.john.firstmenubasic.interfaceDao

import com.john.firstmenubasic.models.User

interface DaoUserInterface {
    fun getDataUser(): List<User>
}