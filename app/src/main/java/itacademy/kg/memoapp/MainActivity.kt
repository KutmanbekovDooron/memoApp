package itacademy.kg.memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import itacademy.kg.memoapp.adapters.MemoAdapter
import itacademy.kg.memoapp.data.Memo
import itacademy.kg.memoapp.fragments.FragmentEditAvatar
import itacademy.kg.memoapp.fragments.MainFragment
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){
    lateinit var taggle: ActionBarDrawerToggle
    lateinit var nav: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_continer,MainFragment())
            commit()
        }

        val drawbalLayout= findViewById<DrawerLayout>(R.id.mainParent)
        nav = findViewById(R.id.nav)

        taggle = ActionBarDrawerToggle(this,drawbalLayout,R.string.Open,R.string.Close)
        drawbalLayout.addDrawerListener(taggle)
        taggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav.setNavigationItemSelectedListener{
            when(it.itemId) {
                R.id.main -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_continer,MainFragment())
                    commit()
                }
                R.id.about -> Toast.makeText(this,"about", Toast.LENGTH_SHORT).show()
                R.id.edit_menu -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_continer,FragmentEditAvatar())
                    commit()
                }
                R.id.exit -> System.exit(0)
            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (taggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}