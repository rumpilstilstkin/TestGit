package com.example.rumpi.testgit.network.usecases

import com.example.rumpi.testgit.models.RepModel
import com.example.rumpi.testgit.network.Endpoints
import io.reactivex.Flowable


///////////////////////////////////////////////////////////////////////////
// Rep Usecases
///////////////////////////////////////////////////////////////////////////

interface RepUsecases {

    fun getReps(query: String): Flowable<List<RepModel>>

}


///////////////////////////////////////////////////////////////////////////
// Rep Usecases Impl
///////////////////////////////////////////////////////////////////////////

class RepUsecasesImpl(
        private val endpoints: Endpoints
) : RepUsecases {

    override fun getReps(query: String): Flowable<List<RepModel>> =
            if (query.isNotEmpty())
                endpoints
                        .searchReps(query)
                        .map { it.items }
                        .applyDefaultNetSchedulers()
            else
                endpoints
                        .getAllReps()
                        .applyDefaultNetSchedulers()

}