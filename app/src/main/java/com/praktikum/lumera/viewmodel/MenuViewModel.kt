package com.praktikum.lumera.viewmodel

import androidx.lifecycle.ViewModel

import com.praktikum.lumera.data.MenuData
import com.praktikum.lumera.model.Menu

class MenuViewModel : ViewModel() {

    // =========================
    // MENU LIST
    // =========================
    val menus =
        MenuData.menuList

    // =========================
    // ADD MENU
    // =========================
    fun addMenu(

        menu: Menu
    ) {

        menus.add(menu)
    }

    // =========================
    // REMOVE MENU
    // =========================
    fun removeMenu(

        menu: Menu
    ) {

        menus.remove(menu)
    }

    // =========================
    // UPDATE MENU
    // =========================
    fun updateMenu(

        oldMenu: Menu,

        newMenu: Menu
    ) {

        val index =
            menus.indexOf(oldMenu)

        if (index != -1) {

            menus[index] = newMenu
        }
    }
}