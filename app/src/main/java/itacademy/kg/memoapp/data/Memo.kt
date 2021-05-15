package itacademy.kg.memoapp.data

import itacademy.kg.memoapp.Utils
import java.io.Serializable
import java.text.FieldPosition
import java.util.*

data class Memo (var memoTitle : String, var memoDesc:  String, var date: Date) : Serializable{

}