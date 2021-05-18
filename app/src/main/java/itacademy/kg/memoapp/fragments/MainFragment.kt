package itacademy.kg.memoapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import itacademy.kg.memoapp.R
import itacademy.kg.memoapp.Utils
import itacademy.kg.memoapp.adapters.MemoAdapter
import itacademy.kg.memoapp.data.Memo
import java.util.*
import itacademy.kg.memoapp.adapters.MemoAdapter.onItamClickListener as onItamClickListener1


class MainFragment : Fragment(), onItamClickListener1 {

    lateinit var memoList: RecyclerView
    lateinit var list:  MutableList<Memo>
    lateinit var listAdaptor: MemoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = returnUser()
        saveToDatabase()

        listAdaptor = MemoAdapter(list,this)

        memoList = view.findViewById(R.id.memoList)
        memoList.layoutManager = LinearLayoutManager(context)
        memoList.setHasFixedSize(true)
        memoList.adapter = listAdaptor

    }

    override fun onItamClick(posision: Int) {

        val bundle = Bundle()
        bundle.putSerializable(Utils.KEY,list[posision])
        val fragment = DetailsFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_continer,fragment)
            addToBackStack(null)
            commit()
        }

        //Fragment -> Fragment - supportFragmentManager - parentFragmentManeger
    }

    override fun ondeleteClick(posision: Int) {
        list.removeAt(posision)
        listAdaptor.notifyDataSetChanged()
        saveToDatabase()
    }

    override fun onEditeClick(posision: Int) {
        val bundle = Bundle()
        bundle.putSerializable(Utils.KEYMEMOEDIT,list[posision])

        bundle.putInt(Utils.INT,posision)
        val fragment = EditFragment()
        fragment.arguments = bundle


        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_continer,fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun setFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_continer,fragment)
            addToBackStack(null)
            commit()

        }
    }

    fun saveToDatabase (){
        val shared = context?.getSharedPreferences(Utils.SHAREDPREFERENCESKEY, Context.MODE_PRIVATE)
        val editer = shared?.edit()
        val gson = Gson()
        editer?.putString(Utils.SHAREDKEY,gson.toJson(list))
        editer?.apply()
    }

    fun returnUser():MutableList<Memo>{
        var memoList: MutableList<Memo>
        val perfs = context?.getSharedPreferences(Utils.SHAREDPREFERENCESKEY,Context.MODE_PRIVATE)
        val gson = Gson()
        val json = perfs?.getString(Utils.SHAREDKEY,null)
        val type = object : TypeToken<MutableList<Memo?>?>() {}.type
        memoList = gson.fromJson(json,type)
        if (memoList == null){
            memoList = mutableListOf()
        }
        return memoList
    }

}