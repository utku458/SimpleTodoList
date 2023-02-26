package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.roomdb.TodoDao
import com.example.myapplication.roomdb.TodoDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoList:ArrayList<TodoModel>
    lateinit var TodoAdapter:TodoAdapter
    lateinit var todoModel:TodoModel
    lateinit var db:TodoDatabase
    lateinit var todoDao: TodoDao
    var placeFromTodo:TodoModel?=null
    val compositeDisposable=CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences("com.example.myapplication", MODE_PRIVATE)


        placeFromTodo= intent.getSerializableExtra("todo") as TodoModel?


         db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java, "Todolar"
        ).build()
        todoDao=db.TodoDao()
       todoList=ArrayList<TodoModel>()

        //todoList= todoDao.getAll() as ArrayList<TodoModel>


        compositeDisposable.add(
            todoDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse)
        )





    }

    fun eklebtn(view: View) {


        if (binding.todoText.text.isEmpty()) {

            binding.textView.setText("yapilacak is gir")

        } else {



        todoModel = TodoModel(binding.todoText.text.toString())
        binding.todoText.setText("")
        //  todoList.add(todoModel)
        var intent = intent

        compositeDisposable.add(

            todoDao.insert(todoModel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponsex)
        )


        /*  todoDao.delete(todoList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse)*/

    }
    }
    fun silbtn(view: View){

        placeFromTodo?.let {
            todoDao.delete(it).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponsex)
        }

    }

    private fun handleResponsex(){

        TodoAdapter.notifyDataSetChanged()
    }

   fun handleResponse(todoListt: List<TodoModel>){
       var gelenIx= intent.getIntExtra("todo",0)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        TodoAdapter= TodoAdapter(todoListt as ArrayList<TodoModel>)
       todoListt.removeAt(gelenIx)
        binding.recyclerView.adapter=TodoAdapter
       TodoAdapter.notifyDataSetChanged()





    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}