package com.example.savvyshopper

import DataStoreManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.savvyshopper.database.room.database.database.ui.options.OptionsScreen

class OptionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreManager.getInstance(this)
            setContent {
                OptionsScreen(onBack = { finish() })
            }
        }
    }
}
