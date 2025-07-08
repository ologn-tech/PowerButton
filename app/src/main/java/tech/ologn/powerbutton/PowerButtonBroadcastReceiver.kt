package tech.ologn.powerbutton;

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
class PowerButtonBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent == null) return
        when (intent.action) {
             context.getString(R.string.intent_action_name) -> {
                val type = intent.getStringExtra("action_type")
                println("Action type: $type")
                when (type) {
                    "double_press" -> {
                        Toast.makeText(context, "Double Press Detected", Toast.LENGTH_SHORT).show()
                    }
                    "mid_long_press" -> {
                        Toast.makeText(context, "Mid-Long Press Detected", Toast.LENGTH_SHORT).show()
                    }
                    "triple_press" -> {
                        Toast.makeText(context, "Triple Press Detected", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}