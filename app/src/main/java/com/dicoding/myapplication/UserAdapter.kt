package com.dicoding.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.myapplication.DetailActivity.Companion.EXTRA_USERNAME
import com.dicoding.myapplication.databinding.ItemUserBinding

//TODO 2 Create Adapter and sync new dependencies
class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val mData = ArrayList<User>()
    fun setData(items: ArrayList<User>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding){
                tvUrl.text = user.url
                tvUsername.text = user.username
                Glide.with(itemView.context)
                    .load(user.photo)
                    .apply(RequestOptions().override(100, 100))
                    .into(ciProfile)

                root.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_USERNAME, user.username)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

}