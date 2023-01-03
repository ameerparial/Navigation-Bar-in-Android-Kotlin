package com.example.navigationbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.navigationbar.fragements.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle:ActionBarDrawerToggle
    lateinit var drawerLayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navBar: NavigationView = findViewById(R.id.nav_bar)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navBar.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.nav_profile -> changeFragment(ProfileFragment(), it.title.toString())
                R.id.nav_home -> changeFragment(HomeFragment(), it.title.toString())
                R.id.nav_bookVeh -> changeFragment(VehicleFragment(), it.title.toString())
                R.id.nav_addWallet -> changeFragment(WalletFragment(), it.title.toString())
                R.id.nav_message -> changeFragment(SMSFragment(), it.title.toString())

            }
            true
        }
        //Setting by default to home Fragment
        val home = navBar.menu[1]
        home.isChecked = true
        changeFragment(HomeFragment(), home.title.toString())

    }

    private fun changeFragment(frag:Fragment, title:String){
        val fragManager = supportFragmentManager
        val fragTrans = fragManager.beginTransaction()
        fragTrans.replace(R.id.frameLayout, frag)
        fragTrans.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}