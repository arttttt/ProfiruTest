package com.arttttt.profirutest.ui

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.arttttt.profirutest.R
import com.arttttt.profirutest.adapters.UsersAdapter
import com.arttttt.profirutest.models.User
import com.arttttt.profirutest.utils.PermissionsManager
import com.vk.sdk.api.*
import com.vk.sdk.api.model.VKList
import com.vk.sdk.api.model.VKApiUser
import kotlinx.android.synthetic.main.activity_users.*


class UsersActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val granted = PermissionsManager
            .getInstance()
            .checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (!granted)
            PermissionsManager
                .getInstance()
                .requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE, null)
        else
            initUsersView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode != PERMISSION_REQUEST_CODE)
            return

        if (permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED)
            initUsersView()

    }

    private fun initUsersView() {
        VKApi
            .friends()
            .get(VKParameters.from(VKApiConst.FIELDS,"id,first_name,last_name,photo_id,photo_max_orig"))
            .executeWithListener(object: VKRequest.VKRequestListener() {
                override fun onComplete(response: VKResponse?) {
                    super.onComplete(response)

                    val list = response?.parsedModel as VKList<VKApiUser>

                    val users: MutableList<User> = mutableListOf()
                    list.forEach {
                        users.add(User(it.first_name, it.last_name, it.photo_max_orig))
                    }

                    usersRecycleView.layoutManager = LinearLayoutManager(this@UsersActivity)
                    usersRecycleView.adapter = UsersAdapter(this@UsersActivity, users)
                }
            })
    }
}
