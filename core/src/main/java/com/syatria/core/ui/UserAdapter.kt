package com.syatria.core.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syatria.core.R
import com.syatria.core.databinding.ItemRowUsersBinding
import com.syatria.core.domain.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    private var listData = ArrayList<User>()
    var onItemClick: ((User) -> Unit)? = null


    fun setData(newListData: List<User>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowUsersBinding.bind(itemView)

        fun bind(data: User) {
            with(binding) {
                Glide.with(itemView.context).load(data.imageUser).into(ivUser)
                tvUsername.text = data.name
                tvCompany.text = data.status

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_users, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}