package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var al :ArrayList<Items>
    lateinit var rv:RecyclerView
    lateinit var fbtn:FloatingActionButton
    lateinit var inputText: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initilizeItem()
        fbtn.setOnClickListener {
            alertShow()

        }
        rvAdapter()
    }

    fun initilizeItem(){
        al = ArrayList()
        rv = findViewById(R.id.recyclerView)
        fbtn = findViewById(R.id.floatingActionButton)
    }
    fun rvAdapter(){

        rv.adapter = MyAdapter(al)
        rv.layoutManager = LinearLayoutManager(this)
    }
    fun alertShow(){
        val db = AlertDialog.Builder(this)
        db.setMessage("ADD ToDo")
        val et = EditText(this)
        et.hint =" Write here"
        db.setView(et)
        db.setPositiveButton("Add",DialogInterface.OnClickListener{

            dialog, which -> run{addItem(et.text.toString())}

        })
        db.setNegativeButton("No",DialogInterface.OnClickListener{
            dialog,id -> dialog.cancel()
        })
        val alert = db.create()
//        alert.setTitle("New")
        alert.show()

    }
    fun addItem(txt:String){
        al.add(Items(txt))
        rv.adapter?.notifyDataSetChanged()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
delete()
        return super.onOptionsItemSelected(item)
    }

    fun delete(){
        var deleted = 0
        for (i in al){
            if (i.ischecked){
                deleted ++
            }
        }
        al.removeAll{ al -> al.ischecked }
        rv.adapter?.notifyDataSetChanged()
   if(deleted == 0){
       Toast.makeText(this,"deleted: $deleted Items",Toast.LENGTH_SHORT).show()

   }else{
       Toast.makeText(this,"deleted: $deleted Items",Toast.LENGTH_SHORT).show()

   }

    }
}


data class Items(val item:String, var ischecked: Boolean = false)