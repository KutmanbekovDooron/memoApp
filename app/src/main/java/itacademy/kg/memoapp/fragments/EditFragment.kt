package itacademy.kg.memoapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import itacademy.kg.memoapp.R
import itacademy.kg.memoapp.Utils
import itacademy.kg.memoapp.adapters.MemoAdapter
import itacademy.kg.memoapp.data.Memo
import java.util.*


class EditFragment : Fragment(){
    lateinit var name: EditText
    lateinit var disc: EditText
    lateinit var chenge: Button
    lateinit var list:MutableList<Memo>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         name = view.findViewById(R.id.editTextTextPersonName)
         disc = view.findViewById(R.id.editTextTextPersonName2)
         chenge = view.findViewById(R.id.chenge_edit)
         list = returnUser()

        val bundle = this.arguments
        val posision = list.get(bundle!!.getInt(Utils.INT))
        name.setText(posision.memoTitle)
        disc.setText(posision.memoDesc)

        val memo = list.removeAt(bundle!!.getInt(Utils.INT))
        chenge.setOnClickListener{
            val memoObject = Memo(name.text.toString(),disc.text.toString(), Date())
            list.add(memoObject)
            saveToDatabase()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_continer,MainFragment())
                addToBackStack(null)
                commit()
            }
        }
        super.onViewCreated(view, savedInstanceState)
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
        val perfs = context?.getSharedPreferences(Utils.SHAREDPREFERENCESKEY, Context.MODE_PRIVATE)
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