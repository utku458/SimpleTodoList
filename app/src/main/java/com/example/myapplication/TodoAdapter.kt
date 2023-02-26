package com.example.myapplication

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.databinding.RecyclerRowBinding
import com.example.myapplication.roomdb.TodoDao
import com.example.myapplication.roomdb.TodoDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class TodoAdapter(var todolist: ArrayList<TodoModel>) : RecyclerView.Adapter<TodoAdapter.TodoHolder>()
{

    class TodoHolder(val recyclerRowBinding: RecyclerRowBinding) :RecyclerView.ViewHolder(recyclerRowBinding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val recyclerRowBinding= RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoHolder(recyclerRowBinding)
    }

    override fun getItemCount(): Int {

        return todolist.size
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {



      //  holder.textView.setText(todolist[position])



        holder.recyclerRowBinding.recyclercheckbox.setText(todolist[position].todo)





        holder.recyclerRowBinding.recyclercheckbox.setOnLongClickListener {



            return@setOnLongClickListener true

        }
            holder.recyclerRowBinding.recyclercheckbox.setOnClickListener {
               // todolist.removeAt(position)

                var intent = Intent(holder.itemView.context,MainActivity::class.java)
                intent.putExtra("todo", todolist.get(position))
                holder.itemView.context.startActivity(intent)



                //todolist.removeAt(position)

                notifyDataSetChanged()


                if (holder.recyclerRowBinding.recyclercheckbox.currentTextColor==Color.RED){
                    holder.recyclerRowBinding.recyclercheckbox.paintFlags=Paint.SUBPIXEL_TEXT_FLAG
                    holder.recyclerRowBinding.recyclercheckbox.setTextColor(Color.BLACK)
                }
                else{
                    holder.recyclerRowBinding.recyclercheckbox.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
                    holder.recyclerRowBinding.recyclercheckbox.setTextColor(Color.RED)
                }
                notifyDataSetChanged()
            }







    }

}