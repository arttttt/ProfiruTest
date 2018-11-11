package com.arttttt.profirutest.adapters

import android.content.Context
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.arttttt.profirutest.models.User
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.arttttt.profirutest.R
import com.arttttt.profirutest.utils.ImageLoader


class UsersAdapter(private val context: Context, private val pictureClickListener: OnPictureClickListener, users: List<User>):
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater
    private val mUsers: List<User>

    private val imageLoader: ImageLoader

    init {
        mInflater = LayoutInflater.from(context)
        mUsers = users

        imageLoader = ImageLoader(context)
    }

    private fun pictureClicked(position: Int, holder: ViewHolder) {
        val user = mUsers[position]

        pictureClickListener.click(holder.avatar, user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.user_item, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.avatar.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                pictureClicked(adapterPosition, viewHolder)
            }
        }

        return viewHolder
    }

    override fun getItemCount() = mUsers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mUsers[position]

        holder.firstName.text = user.firstName
        holder.lastName.text = user.lastName

        imageLoader.displayImage(user.avatarUrl, holder.avatar)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val avatar: ImageView
        val firstName: TextView
        val lastName: TextView

        init {
            avatar = view.findViewById(R.id.avatar)
            firstName = view.findViewById(R.id.first_name)
            lastName = view.findViewById(R.id.last_name)
        }
    }

    interface OnPictureClickListener {
        fun click(view: View, data: Parcelable)
    }
}