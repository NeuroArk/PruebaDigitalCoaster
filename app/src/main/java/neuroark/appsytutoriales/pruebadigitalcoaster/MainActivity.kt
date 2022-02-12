package neuroark.appsytutoriales.pruebadigitalcoaster

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var  navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val toolBarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val naview: NavigationView = findViewById(R.id.navigationView)
        toolBarLayout.title = ""
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(this, navController)
        naview.setNavigationItemSelectedListener{ clickNavItem(it) }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    fun clickNavItem(item: MenuItem) : Boolean{
        when (item.itemId) {
            R.id.item_navslid_actividad_1 -> {
                navController.navigate(R.id.action_global_actividad_1)
                return true
            }
            R.id.item_navslid_actividad_2 -> {
                navController.navigate(R.id.action_global_actividad_2)
                return true
            }
            R.id.item_navslid_actividad_3 -> {
                navController.navigate(R.id.action_global_actividad_3)
                return true
            }
            R.id.item_navslid_actividad_4 -> {
                navController.navigate(R.id.action_global_actividad_4)
                return true
            }
            else -> {
                navController.navigate(R.id.action_global_navegador)
                return true
            }
        }
    }
}