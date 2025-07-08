package tech.ologn.powerdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    
    private lateinit var powerButtonReceiver: PowerButtonBroadcastReceiver
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        startPowerService()
        registerPowerButtonReceiver()
    }

    private fun startPowerService() {
        val intent = Intent(this, ForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }
    
    private fun registerPowerButtonReceiver() {
        powerButtonReceiver = PowerButtonBroadcastReceiver()
        val filter = IntentFilter(getString(R.string.intent_action_name))
        registerReceiver(powerButtonReceiver, filter, RECEIVER_EXPORTED)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (::powerButtonReceiver.isInitialized) {
            unregisterReceiver(powerButtonReceiver)
        }
    }
}
