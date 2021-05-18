package itacademy.kg.memoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintSet
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import itacademy.kg.memoapp.adapters.MemoAdapter
import itacademy.kg.memoapp.data.Memo
import itacademy.kg.memoapp.fragments.FragmentEditAvatar
import itacademy.kg.memoapp.fragments.MainFragment
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var taggle: ActionBarDrawerToggle
    lateinit var nav: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_continer, MainFragment())
            commit()
        }

        findViewById<FloatingActionButton>(R.id.fag).setOnClickListener{
            showAddPosts()
        }

        val drawbalLayout = findViewById<DrawerLayout>(R.id.mainParent)
        nav = findViewById(R.id.nav)

        taggle = ActionBarDrawerToggle(this, drawbalLayout, R.string.Open, R.string.Close)
        drawbalLayout.addDrawerListener(taggle)
        taggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_continer, MainFragment())
                    commit()
                }
                R.id.about -> Toast.makeText(this, "about", Toast.LENGTH_SHORT).show()
                R.id.edit_menu -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_continer, FragmentEditAvatar())
                    commit()
                }
                R.id.exit -> System.exit(0)
            }
            true
        }
    }

    private fun showAddPosts() {
        val view = LayoutInflater.from(this).inflate(R.layout.list_button,null)
        val name = view.findViewById<EditText>(R.id.name_button)
        val disc =  view.findViewById<EditText>(R.id.discription_button)
        val data = Date()

        val dialog = AlertDialog.Builder(this)
                .setTitle("Add post")
                .setIcon(R.drawable.ic_baseline_add_box_24)
                .setView(view)
                .setPositiveButton("Add post"){_,_ ->
                    if (name.text.isNotEmpty()&&disc.text.isNotEmpty()){
                        val memo = Memo(name.text.toString(),disc.text.toString(),data)
                        val sharedPreferences = getSharedPreferences(Utils.SHAREDPREFERENCESKEY,Context.MODE_PRIVATE)
                        val gson = Gson()
                        val json = sharedPreferences?.getString(Utils.SHAREDKEY,null)
                        val type = object : TypeToken<MutableList<Memo?>?>() {}.type
                        val listMemo: MutableList<Memo> = gson.fromJson(json,type)
                        listMemo.add(memo)
                        setSharedPreferences(listMemo)
                    }
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_continer, MainFragment())
                        commit()
                    }

                }
                .setNegativeButton("Cancel"){_,_ ->
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_continer, MainFragment())
                        commit()
                    }
                }
                .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (taggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun setSharedPreferences (list:MutableList<Memo>){
        val shared = this?.getSharedPreferences(Utils.SHAREDPREFERENCESKEY, Context.MODE_PRIVATE)
        val editer = shared?.edit()
        val gson = Gson()
        editer?.putString(Utils.SHAREDKEY,gson.toJson(list))
        editer?.apply()
    }

}