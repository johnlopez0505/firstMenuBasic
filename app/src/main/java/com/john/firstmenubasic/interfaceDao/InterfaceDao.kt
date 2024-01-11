package com.john.firstmenubasic.interfaceDao

import com.john.firstmenubasic.models.Restaurant

interface InterfaceDao {
    fun getDataRestaurant(): List<Restaurant>
}