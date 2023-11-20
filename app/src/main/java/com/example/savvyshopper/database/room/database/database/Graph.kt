package com.example.savvyshopper.database.room.database.database

import android.content.Context
import com.example.savvyshopper.repository.Repository

object Graph {

    lateinit var database: ShoppingListDatabase
    private set

    val repository by lazy {
        Repository(
            listDao = database.listDao(),
            itemDao = database.itemDao()
        )
    }

    fun provide(context: Context) {
        database = ShoppingListDatabase.getDatabase(context)
    }


}