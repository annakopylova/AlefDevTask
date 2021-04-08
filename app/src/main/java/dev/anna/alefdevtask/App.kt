package dev.anna.alefdevtask

import android.app.Application
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.facebook.drawee.backends.pipeline.Fresco
import dev.anna.core.navigation.Router
import dev.anna.core.navigation.Screen

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this);
    }
}