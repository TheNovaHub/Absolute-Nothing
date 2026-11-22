package com.mutlaqo.hechnima

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
            or android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        scheduleNothingNotifications()
        setContent {
            var showExit by remember { mutableStateOf(false) }
            val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { }
            LaunchedEffect(Unit) {
                if (Build.VERSION.SDK_INT >= 33) permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            BackHandler { showExit = true }
            Box(Modifier.fillMaxSize().background(Color.Black))
            if (showExit) {
                val message = remember { RandomNothing.exitMessages[Random.nextInt(RandomNothing.exitMessages.size)] }
                AlertDialog(
                    onDismissRequest = { showExit = false },
                    containerColor = Color(0xFF151515),
                    title = { Text("MUTLAQO HECH NIMA", color = Color.White) },
                    text = { Text(message, color = Color(0xFFCCCCCC), textAlign = TextAlign.Start) },
                    confirmButton = {
                        TextButton(onClick = { showExit = false }) { Text("Yana hech narsa qilamiz", color = Color.White) }
                    },
                    dismissButton = {
                        TextButton(onClick = { finish() }) { Text("Chiqish", color = Color(0xFFAAAAAA)) }
                    }
                )
            }
        }
    }

    private fun scheduleNothingNotifications() {
        val request = PeriodicWorkRequestBuilder<NotificationWorker>(6, TimeUnit.HOURS)
            .addTag("nothing-notifications")
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "nothing-periodic-notifications", ExistingPeriodicWorkPolicy.KEEP, request
        )
    }
}
