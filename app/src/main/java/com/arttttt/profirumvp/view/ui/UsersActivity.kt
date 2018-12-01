package com.arttttt.profirumvp.view.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.view.adapters.UsersAdapter
import com.arttttt.profirumvp.model.user.User
import com.arttttt.profirumvp.model.user.UsersDataSourceImpl
import com.arttttt.profirumvp.model.user.UsersRepositoryImpl
import com.arttttt.profirumvp.presenter.users.UsersContract
import com.arttttt.profirumvp.presenter.users.UsersPresenter
import com.arttttt.profirumvp.utils.ActivityUtils
import com.arttttt.profirumvp.utils.PermissionsUtils
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity(), UsersContract.View {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 0

        fun start(context: Context) {
            ActivityUtils.startActivity<UsersActivity>(context,
                null,
                null,
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
    }

    private val mPresenter = UsersPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val granted = PermissionsUtils
            .getInstance()
            .checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (!granted)
            PermissionsUtils
                .getInstance()
                .requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE, null)
        else
            mPresenter.getUsers(UsersRepositoryImpl.getInstance(UsersDataSourceImpl()))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode != PERMISSION_REQUEST_CODE)
            return

        if (permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED)
            mPresenter.getUsers(UsersRepositoryImpl.getInstance(UsersDataSourceImpl()))
    }

    override fun showErrorMessage(message: String) {
        errorMessage.text = message
    }

    override fun showLoadingIndicator(show: Boolean) {
        if (show)
            usersLoading.show()
        else
            usersLoading.hide()
    }

    override fun showUsers(users: List<User>) {
        with(usersRecyclerView) {
            layoutManager = LinearLayoutManager(this@UsersActivity)
            adapter = UsersAdapter(object : UsersAdapter.PhotoClickListener {
                override fun onPhotoClick(url: String, sharedViewId: Int, position: Int) {
                    val sharedView = layoutManager
                        ?.findViewByPosition(position)
                        ?.findViewById<View>(sharedViewId)
                    if (sharedView != null)
                        mPresenter.openUserPhoto(sharedView, url)
                }
            }).apply {
                presenter.putUsers(users)
            }
        }
    }

    override fun startPhotoActivity(view: View, url: String) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
            view, "photo")

        ActivityUtils.startActivity<PhotoActivity>(this,
            options.toBundle(),
            Bundle().apply { putString(PhotoActivity.EXTRA_PHOTO_URL_ID, url) },
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
}
