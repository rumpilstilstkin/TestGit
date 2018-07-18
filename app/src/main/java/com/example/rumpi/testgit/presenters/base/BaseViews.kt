package com.example.rumpi.testgit.presenters.base


///////////////////////////////////////////////////////////////////////////
// Base View
///////////////////////////////////////////////////////////////////////////

interface BaseView {
    fun showError(message: String)
}


///////////////////////////////////////////////////////////////////////////
// Base Loading View
///////////////////////////////////////////////////////////////////////////

interface BaseLoadingView : BaseView {
    fun showLoading()
    fun hideLoading()
}