package com.example.roomdemo


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.databinding.ListItemBinding
import com.example.roomdemo.db.Subcriber


//class MyRecyclerViewAdapter(private val subscriberList: List<Subcriber>):
//unit bisa di buat otomatis dengan cara membuat fungsi clicknya dulu
//setelah otomatis ada permintaan perubahan di konstruktor MyRecyclewView
class MyRecyclerViewAdapter(
    private val subscriberList: List<Subcriber>,
    private val clickListener: (Subcriber) -> Unit
):
    RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val binding:ListItemBinding=
            DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position],clickListener)

    }


    override fun getItemCount(): Int {
       return subscriberList.size
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
//   fun bind(subscriber: Subcriber){
fun bind(subscriber: Subcriber,clickListener: (Subcriber) -> Unit){
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItemLayout.setOnClickListener{
            clickListener(subscriber)
        }
   }

}
