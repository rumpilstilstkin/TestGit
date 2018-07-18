package com.example.rumpi.testgit.presenters.base

import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription


///////////////////////////////////////////////////////////////////////////
// Base Rx Presenter
///////////////////////////////////////////////////////////////////////////

abstract class BaseRxPresenter<Model, View : BaseLoadingView> : Subscriber<Model> {

    protected var view: View? = null

    open fun attachView(view: View) {
        this.view = view
    }

    override fun onError(t: Throwable?) {
        view?.hideLoading()
        t?.let { view?.showError(t.message ?: "") }
    }

    override fun onSubscribe(s: Subscription?) {
        s?.request(Long.MAX_VALUE)
    }

    override fun onComplete() {
        view?.hideLoading()
    }
}