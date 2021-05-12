package itacademy.kg.memoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import itacademy.kg.memoapp.R
import itacademy.kg.memoapp.Utils
import itacademy.kg.memoapp.data.Memo
import java.text.SimpleDateFormat
import java.util.*


class DetailsFragment : Fragment() {
    lateinit var list:MutableList<Memo>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = view.findViewById<TextView>(R.id.name_2)
        val discription = view.findViewById<TextView>(R.id.discription_2)
        val data = view.findViewById<TextView>(R.id.data_2)

        val bundle = this.arguments
        val memo= bundle?.getSerializable(Utils.KEY) as Memo //Memo))

        name.setText(memo.memoTitle)
        discription.setText(memo.memoDesc)
        data.setText(memo.date.toString())

    }

}