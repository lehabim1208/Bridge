package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    val launchIntent = packageManager.getLaunchIntentForPackage("com.walmart.sparkdriver.mx")
    if (launchIntent != null) {
      launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      startActivity(launchIntent)
    } else {
      try {
        val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.walmart.sparkdriver.mx"))
        playStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(playStoreIntent)
      } catch (e: android.content.ActivityNotFoundException) {
        val webStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.walmart.sparkdriver.mx"))
        webStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(webStoreIntent)
        } catch (e2: android.content.ActivityNotFoundException) {
            // Emulador sin navegador
        }
      }
    }
    finish() // Cerrar la app puente inmediatamente
  }
}
