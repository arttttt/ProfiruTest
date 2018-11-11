package com.arttttt.profirutest.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.arttttt.profirutest.models.User
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.arttttt.profirutest.R
import com.arttttt.profirutest.ui.FullscreenImageActivity
import com.arttttt.profirutest.utils.ImageLoader
import android.support.v4.app.ActivityOptionsCompat

class UsersAdapter(private val context: Context, users: List<User>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater
    private val mUsers: List<User>

    private val imageLoader: ImageLoader

    init {
        mInflater = LayoutInflater.from(context)
        mUsers = users

        imageLoader = ImageLoader(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.user_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = mUsers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mUsers[position]

        holder.firstName.text = user.firstName
        holder.lastName.text = user.lastName

        imageLoader.displayImage(user.avatarUrl, holder.avatar)

        holder.avatar.setOnClickListener {
            val intent = Intent(context, FullscreenImageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            intent.putExtras(bundle)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity,
                    holder.avatar, "avatar")
                context.startActivity(intent, options.toBundle())
            } else {
                context.startActivity(intent)
            }
        }
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
}