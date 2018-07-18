package com.example.rumpi.testgit.network.usecases

import com.example.rumpi.testgit.models.UserModel
import com.example.rumpi.testgit.network.Endpoints
import io.reactivex.Flowable


///////////////////////////////////////////////////////////////////////////
// User Usecases
///////////////////////////////////////////////////////////////////////////

interface UserUsecases {

    fun getUser(username: String): Flowable<UserModel>

}


///////////////////////////////////////////////////////////////////////////
// User Usecases Impl
///////////////////////////////////////////////////////////////////////////

class UserUsecasesImpl(
        private val endpoints: Endpoints
) : UserUsecases {

    override fun getUser(username: String): Flowable<UserModel> =
            endpoints
                    .getUser(username)
                    .applyDefaultNetSchedulers()

}