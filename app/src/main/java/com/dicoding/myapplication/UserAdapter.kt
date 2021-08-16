package com.dicoding.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

//TODO 2 Create Adapter and sync new dependencies
class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val mData = ArrayList<User>()
    fun setData(items: ArrayList<User>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var photo = view.findViewById<CircleImageView>(R.id.ci_profile)
        private var url = view.findViewById<TextView>(R.id.tv_url)
        private var username = view.findViewById<TextView>(R.id.tv_username)
        fun bind(user: User) {
            with(itemView){
                url.text = user.url
                username.text = user.username
                Glide.with(itemView.context)
                    .load(user.photo)
                    .apply(RequestOptions().override(100, 100))
                    .into(photo)
            }
        }

    }

}