package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.Subcriber
import com.example.roomdemo.db.SubcriberDAO
import com.example.roomdemo.db.SubscriberDatabase
import com.example.roomdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var subcriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val dao:SubcriberDAO = SubscriberDatabase.getInstance(application).subcriberDAO //database instance
        val repository = SubscriberRepository(dao) //repository instance
        val factory =  SubscriberViewModelFactory(repository) // factory instance
        // 3 tahap baru bisa menggunakan subcriberViewModel yang dideklarasi global di atas
        //get harus di import dulu
        subcriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)

        //myViewModel itu berasal dari deklarasi dalam xml layoutnya
        binding.myViewModel = subcriberViewModel
        binding.lifecycleOwner = this
        //lifecycleOwner itu berasal dari library ViewDataBinding

        //video 00:53

        //displaySubscribersList()
        initRecyclerView()
        subcriberViewModel.message.observe(this,Observer{
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySubscribersList()
    }
    private fun displaySubscribersList(){
        subcriberViewModel.subscribers.observe(this, Observer {
            Log.i("MYTAG",it.toString())

           // binding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(it)
            binding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(it) { selectedItem: Subcriber ->
                listitemClicked(selectedItem)
            }
        })

    }

    private fun listitemClicked(subcriber: Subcriber){
        Toast.makeText(this,"selected name is ${subcriber.name}",Toast.LENGTH_LONG).show()
        subcriberViewModel.initUpdateAndDelete(subcriber)
    }
}