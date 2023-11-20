package com.example.savvyshopper.database.room.database.database

import android.app.Application

class SavvyShoppingApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}