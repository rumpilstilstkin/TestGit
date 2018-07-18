package com.example.rumpi.testgit.presenters

import com.example.rumpi.testgit.models.UserModel
import com.example.rumpi.testgit.network.usecases.UserUsecases
import com.example.rumpi.testgit.presenters.base.BaseLoadingView
import com.example.rumpi.testgit.presenters.base.BaseRxPresenter


///////////////////////////////////////////////////////////////////////////
// User Details View
///////////////////////////////////////////////////////////////////////////

interface UserDetailsView : BaseLoadingView {
    fun setName(name: String?)
    fun setAvatar(url: String?)
}


///////////////////////////////////////////////////////////////////////////
// User Details Presenter
///////////////////////////////////////////////////////////////////////////

class UserDetailsPresenter(val usecases: UserUsecases) : BaseRxPresenter<UserModel, UserDetailsView>() {

    fun getUser(userName: String?) {
        usecases.getUser(userName?: "").subscribe(this)
    }

    override fun onNext(t: UserModel?) {
        view?.setName(t?.login)
        view?.setAvatar(t?.avatar_url)
    }

}