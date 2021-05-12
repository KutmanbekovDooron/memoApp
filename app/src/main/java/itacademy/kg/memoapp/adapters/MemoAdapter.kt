package itacademy.kg.memoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.memoapp.R
import itacademy.kg.memoapp.data.Memo
import java.util.zip.Inflater

class MemoAdapter (memoList:List<Memo>, var listener:onItamClickListener) : RecyclerView.Adapter<MemoAdapter.MemoHolder>() {


     inner class MemoHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
         val name = itemView.findViewById<TextView>(R.id.name)
         val discripton = itemView.findViewById<TextView>(R.id.discription)
         private val noteDelete: ImageView = itemView.findViewById(R.id.delete)
         private val noteEdit:ImageView = itemView.findViewById(R.id.edite)
         private val parent: CardView = itemView.findViewById(R.id.parent)

         init {
             parent.setOnClickListener(this)
             noteEdit.setOnClickListener(this)
             noteDelete.setOnClickListener(this)
         }

         override fun onClick(v: View?) {
             when(v?.id){
                 R.id.parent -> listener.onItamClick(adapterPosition)
                 R.id.edite -> listener.onEditeClick(adapterPosition)
                 R.id.delete -> listener.ondeleteClick(adapterPosition)
             }
         }


     }

    val list = memoList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.mamo_itam,parent,false)
        return MemoHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MemoHolder, position: Int) {
        holder.itemView.apply {
            holder.name.text = list[position].memoTitle
            holder.discripton.text = list[position].memoDesc

        }
    }

    interface onItamClickListener{
        fun onItamClick(posision:Int)
        fun ondeleteClick(posision: Int)
        fun onEditeClick(posision: Int)

    }


}
