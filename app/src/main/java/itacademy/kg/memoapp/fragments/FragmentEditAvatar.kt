package itacademy.kg.memoapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import itacademy.kg.memoapp.R

class FragmentEditAvatar : Fragment(), View.OnClickListener{

    lateinit var avatar : ImageView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_avatar, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = view.findViewById<EditText>(R.id.name_edit)
        val number = view.findViewById<EditText>(R.id.number_avatar)
        val gmail = view.findViewById<EditText>(R.id.gmail_avatar)

        val resoult = name.text.toString()

        val view = LayoutInflater.from(context).inflate(R.layout.header,null)
        val avatarMain = view.findViewById<ImageView>(R.id.avatar)


        //avatars
        view.findViewById<ImageView>(R.id.avatar1).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar2).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar3).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar4).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar5).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar6).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar7).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar8).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar9).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar10).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar11).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.avatar12).setOnClickListener(this)

        //backgrounds
        view.findViewById<ImageView>(R.id.dark_background).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.sky_background).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.blue_background).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.blue2_background).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.blue3_background).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.black_blue_background).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.black_background).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.pink_background).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.white_background).setOnClickListener(this)


        view.findViewById<Button>(R.id.chenge_button).setOnClickListener{
            avatarMain.setImageResource(avatar.id)
        }

        super.onViewCreated(view, savedInstanceState)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.avatar1 -> avatar = v.findViewById(R.id.avatar1)
            R.id.avatar2 -> avatar = v.findViewById(R.id.avatar2)
            R.id.avatar3 -> avatar = v.findViewById(R.id.avatar3)
            R.id.avatar4 -> avatar = v.findViewById(R.id.avatar4)
            R.id.avatar5 -> avatar.setImageResource(v.id)
            R.id.avatar6 -> avatar.setImageResource(v.id)
            R.id.avatar7 -> avatar.setImageResource(v.id)
        }
    }


}