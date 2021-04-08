package dev.anna.alefdevtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dev.anna.core.navigation.ContentScreen
import dev.anna.core.navigation.Router
import dev.anna.core.navigation.Screen

class MainActivity : AppCompatActivity(), Router {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun findChildController(): NavController? {
        val nestedNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragments_container) as? NavHostFragment
        return nestedNavHostFragment?.navController
    }

    override fun goTo(screen: Screen) {
        when (screen) {
            is ContentScreen -> {
                findChildController()?.navigate(
                    MainGrafDirections.actionGlobalFragmentContent(
                        screen.url
                    )
                )
            }
        }
    }
}