package com.example.rumpi.testgit.presenters

import com.example.rumpi.testgit.models.RepModel
import com.example.rumpi.testgit.network.usecases.GitUsecases
import com.example.rumpi.testgit.presenters.base.BaseLoadingView
import com.example.rumpi.testgit.presenters.base.BaseRxPresenter
import java.util.Timer
import java.util.TimerTask


///////////////////////////////////////////////////////////////////////////
// Search Reps View
///////////////////////////////////////////////////////////////////////////

interface SearchRepsView : BaseLoadingView {
    fun setReps(reps: List<RepModel>?)
}


///////////////////////////////////////////////////////////////////////////
// Search Reps Presenter
///////////////////////////////////////////////////////////////////////////

class SearchRepsPresenter(val usecases: GitUsecases) : BaseRxPresenter<List<RepModel>, SearchRepsView>() {

    private var qwery: String = ""
    private var isLoading = false
    private var isPostRun = false

    fun getReps(qwery: String) {
        this.qwery = qwery
        if (!isLoading) {
            isLoading = true
            usecases.getReps(qwery).subscribe(this)
        }
        else {
            postGetReps()
        }
    }

    private fun postGetReps() {
        if (!isPostRun) {
            isPostRun = true
            Timer().schedule(
                    object : TimerTask() {
                        override fun run() {
                            isPostRun = false
                            getReps(qwery)
                        }
                    },
                    700)
        }
    }

    override fun onNext(t: List<RepModel>?) {
        view?.setReps(t)
        isLoading = false
    }

    override fun onError(t: Throwable?) {
        super.onError(t)
        isLoading = false
    }

}