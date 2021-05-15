package itacademy.kg.memoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.memoapp.R
import itacademy.kg.memoapp.Utils
import itacademy.kg.memoapp.adapters.MemoAdapter
import itacademy.kg.memoapp.data.Memo
import java.util.*
import kotlin.collections.ArrayList
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
        list = mutableListOf()
        list.add(Memo("Shopping","Продукты", Date()))
        list.add(Memo("Shopping","vkjcbkxfm", Date()))
        list.add(Memo("gnvgjn","Продукты", Date()))
        list.add(Memo("fhngnnvmbnfm","gngvjmghjmvnd", Date()))
        list.add(Memo("Shhmbjmh,n b, opping","Продfnghbtgnfhgукты", Date()))

        listAdaptor = MemoAdapter(list,this)
        Utils.detailsList = list

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

    }

    override fun onEditeClick(posision: Int) {
        val bundle = Bundle()
        bundle.putSerializable(Utils.KEYMEMOEDIT,list[posision])
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

}