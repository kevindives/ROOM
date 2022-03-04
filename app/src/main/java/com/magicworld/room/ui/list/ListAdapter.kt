package com.magicworld.room.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.magicworld.room.R
import com.magicworld.room.model.User

class ListAdapter (
    private val userList : MutableList<User>,
    private val onItemClicked: (User) -> Unit
        ): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.itemView.setOnClickListener{onItemClicked(userList[position])}
        holder.bind(user)
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    fun appendItems(newItems: MutableList<User>) {
        userList.clear()
        userList.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var idtxt: TextView = itemView.findViewById(R.id.id_txt)
        private var firstNametxt: TextView = itemView.findViewById(R.id.firstName_txt)
        private var lastNametxt: TextView = itemView.findViewById(R.id.lastName_txt)
        private var agetxt: TextView = itemView.findViewById(R.id.age_txt)

        fun bind(user: User) {

            idtxt.text = user.id.toString()
            firstNametxt.text = user.firstName
            lastNametxt.text = user.lastName
            agetxt.text = user.age.toString()

        }
    }

}