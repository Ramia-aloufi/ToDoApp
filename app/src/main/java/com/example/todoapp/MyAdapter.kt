package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater
import kotlinx.android.synthetic.main.todo.view.*

class MyAdapter(val ToDoList:ArrayList<Items>):RecyclerView.Adapter<MyAdapter.VH>() {
    class VH(MyAdapter:View):RecyclerView.ViewHolder(MyAdapter) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
      return VH(LayoutInflater.from(parent.context).inflate(R.layout.todo,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.apply {
            tv.text = ToDoList[position].item
            cb.isChecked = ToDoList[position].ischecked
            cb.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    tv.setTextColor(Color.GREEN)
                }else{
                    tv.setTextColor(Color.BLACK)
                }
                ToDoList[position].ischecked = !ToDoList[position].ischecked
            }

        }

    }

    override fun getItemCount(): Int = ToDoList.size
}