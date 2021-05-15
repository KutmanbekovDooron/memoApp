package itacademy.kg.memoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import itacademy.kg.memoapp.R
import itacademy.kg.memoapp.Utils
import itacademy.kg.memoapp.adapters.MemoAdapter
import itacademy.kg.memoapp.data.Memo


class EditFragment : Fragment(){
    lateinit var name: EditText
    lateinit var disc: EditText
    lateinit var chenge: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         name = view.findViewById(R.id.editTextTextPersonName)
         disc = view.findViewById(R.id.editTextTextPersonName2)
        chenge = view.findViewById(R.id.chenge_edit)


        val bundle = this.arguments
        val memo = bundle?.getSerializable(Utils.KEYMEMOEDIT) as Memo

        chenge.setOnClickListener{
            memo.memoTitle = name.text.toString()
            memo.memoDesc = disc.text.toString()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_continer,MainFragment())
                addToBackStack(null)
                commit()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }


}