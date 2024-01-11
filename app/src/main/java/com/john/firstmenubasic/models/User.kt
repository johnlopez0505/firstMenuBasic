package com.john.firstmenubasic.models

class User (
    var name: String,
    var email: String,
    var password: String
)
{
    override fun toString(): String {
        return "Usuario(name='$name', email='$email', password='$password')"
    }
}