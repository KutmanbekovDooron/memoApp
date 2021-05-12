package itacademy.kg.memoapp.data

import itacademy.kg.memoapp.Utils
import java.io.Serializable
import java.text.FieldPosition
import java.util.*

data class Memo (val memoTitle : String, val memoDesc:  String, val date: Date) : Serializable{

}