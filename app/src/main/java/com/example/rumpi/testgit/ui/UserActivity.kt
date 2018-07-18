package com.example.rumpi.testgit.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.bindView
import com.bumptech.glide.Glide
import com.example.rumpi.testgit.GitApplication
import com.example.rumpi.testgit.R
import com.example.rumpi.testgit.presenters.UserDetailsPresenter
import com.example.rumpi.testgit.presenters.UserDetailsView
import org.jetbrains.anko.intentFor


///////////////////////////////////////////////////////////////////////////
// User Activity
///////////////////////////////////////////////////////////////////////////

class UserActivity : AppCompatActivity(), UserDetailsView {
    companion object {
        val USER_NAME = "user_name"

        fun launch(context: Context, userName: String) {
            val intent = context.intentFor<UserActivity>()
            intent.putExtra(USER_NAME, userName)
            context.startActivity(intent)
        }
    }

    private val avatarView by bindView<ImageView>(R.id.au_iv_avatar)
    private val nameView by bindView<TextView>(R.id.au_tv_name)
    private val toolbar by bindView<Toolbar>(R.id.toolbar)

    private var presenter: UserDetailsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = UserDetailsPresenter(GitApplication.appComponent.baseUsecases())
        presenter?.attachView(this)
        presenter?.getUser(intent.getStringExtra(USER_NAME))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                android.R.id.home -> {
                    finish(); true
                }
                else -> super.onOptionsItemSelected(item)
            }

    ///////////////////////////////////////////////////////////////////////////
    // Setters
    ///////////////////////////////////////////////////////////////////////////

    override fun setAvatar(url: String?) {
        Glide.with(this)
                .load(url)
                .into(avatarView)
    }

    override fun setName(name: String?) {
        nameView.text = name
    }

    ///////////////////////////////////////////////////////////////////////////
    // Actions
    ///////////////////////////////////////////////////////////////////////////

    override fun hideLoading() {
        //nope
    }

    override fun showLoading() {
        //nope
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}