package itacademy.kg.memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import itacademy.kg.memoapp.adapters.MemoAdapter
import itacademy.kg.memoapp.data.Memo
import itacademy.kg.memoapp.fragments.MainFragment
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_continer,MainFragment())
            commit()
        }


    }
}