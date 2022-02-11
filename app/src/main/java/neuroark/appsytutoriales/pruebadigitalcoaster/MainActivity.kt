package neuroark.appsytutoriales.pruebadigitalcoaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {
    lateinit var  navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val toolBarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolBarLayout.title = ""
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(this, navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}