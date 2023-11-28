package com.example.savvyshopper

import DataStoreManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.savvyshopper.database.room.database.database.ui.SavvyShopperNavigation
import com.example.savvyshopper.ui.theme.SavvyShopperTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productId = intent?.extras?.getInt("productId") ?: -1

        setContent {
            SavvyShopperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SavvyShopperApp(productId)
                }
            }
        }
    }

    @Composable
    fun SavvyShopperApp(productId: Int) {
        SavvyShopperNavigation(productId)
    }
}
