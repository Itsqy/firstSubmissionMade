package com.syatria.core.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syatria.core.R
import com.syatria.core.databinding.ItemRowUsersBinding
import com.syatria.core.domain.model.User

class UserAdapter(private val onItemClick: (User) -> Unit) :
    ListAdapter<User, UserAdapter.ListViewHolder>(DIFF_CALLBACK) {


//    fun setData(newListData: List<User>?) {
//        if (newListData == null) return
//        val diffUtilCallback = DiffUtils(listData, newListData)
//        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
//        listData.clear()
//        listData.addAll(newListData)
////        diffResult.dispatchUpdatesTo(this)
//        notifLyDataSetChanged()
//
//
//    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowUsersBinding.bind(itemView)

        fun bind(data: User) {
            with(binding) {
                Glide.with(itemView.context).load(data.imageUser).into(ivUser)
                tvUsername.text = data.name
                tvCompany.text = data.status

                itemView.setOnClickListener {
                    onItemClick.invoke(data)

                }

            }
        }

//        init {
//            binding.root.setOnClickListener {
//                onItemClick?.invoke(listData[adapterPosition])
//            }
//        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_users, parent, false)
        )
    }

//    override fun getItemCount(): Int {
//        return listData.size
//    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<User> =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(
                    oldUser: User, newUser: User
                ): Boolean {
                    return oldUser.userId == newUser.userId
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: User, newUser: User
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }
}


//class DiffUtils(
//    private val mOldUserList: List<User>,
//    private val mNewUserList: List<User>,
//) :
//    DiffUtil.Callback() {
//    override fun getOldListSize(): Int {
//        return mOldUserList.size
//    }
//
//    override fun getNewListSize(): Int {
//        return mNewUserList.size
//    }
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return mOldUserList[oldItemPosition].userId == mNewUserList[newItemPosition].userId
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        val oldListUser = mOldUserList[oldItemPosition]
//        val newListUser = mNewUserList[newItemPosition]
//        return oldListUser.name == newListUser.name && oldListUser.userId == newListUser.userId
//    }
//
//    @Nullable
//    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
//        return super.getChangePayload(oldPosition, newPosition)
//    }
//}
