package com.john.firstmenubasic.object_model

import com.john.firstmenubasic.models.User

object UserRepository {
    val listUser : MutableList<User> = mutableListOf(
        User("john","lopezcon1@hotmail.com","1234"),
        User("juan","juan@hotmail.com","1234"),
        User("sonia","sonia@hotmail.com","1234"),
        User("dario","dario@hotmail.com","1234"),
        User("francisco","francisco@hotmail.com","1234")
    )
}