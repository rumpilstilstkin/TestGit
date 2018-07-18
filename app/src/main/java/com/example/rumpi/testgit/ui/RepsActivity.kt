package com.example.rumpi.testgit.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import butterknife.bindView
import com.example.rumpi.testgit.GitApplication
import com.example.rumpi.testgit.R
import com.example.rumpi.testgit.models.RepModel
import com.example.rumpi.testgit.presenters.SearchRepsPresenter
import com.example.rumpi.testgit.presenters.SearchRepsView
import com.example.rumpi.testgit.ui.adapters.RepAdapter
import com.example.rumpi.testgit.ui.adapters.RepCallbacks


///////////////////////////////////////////////////////////////////////////
// Reps Activity
///////////////////////////////////////////////////////////////////////////

class RepsActivity : AppCompatActivity(),
                     RepCallbacks,
                     SearchRepsView,
                     SwipeRefreshLayout.OnRefreshListener,
                     TextWatcher {

    private val repsList by bindView<RecyclerView>(R.id.ar_rv_reps)
    private val swipe by bindView<SwipeRefreshLayout>(R.id.ar_swipe)
    private val searchView by bindView<EditText>(R.id.ar_et_search)

    private var adapter: RepAdapter? = null

    private var presenter: SearchRepsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reps)

        initRecycler()

        searchView.addTextChangedListener(this)

        presenter = SearchRepsPresenter(GitApplication.appComponent.baseUsecases())
        presenter?.attachView(this)
        presenter?.getReps(searchView.text.toString())
    }

    private fun initRecycler() {
        repsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        repsList.itemAnimator = DefaultItemAnimator()
        adapter = RepAdapter(this, this)
        repsList.adapter = adapter
        swipe.setOnRefreshListener(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Search
    ///////////////////////////////////////////////////////////////////////////

    override fun afterTextChanged(text: Editable?) {
        presenter?.getReps(text.toString())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //nope
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //nope
    }

    ///////////////////////////////////////////////////////////////////////////
    // Setters
    ///////////////////////////////////////////////////////////////////////////

    override fun setReps(reps: List<RepModel>?) {
        adapter?.setItems(reps ?: emptyList())
    }

    ///////////////////////////////////////////////////////////////////////////
    // Actions
    ///////////////////////////////////////////////////////////////////////////

    override fun onRepClick(userName: String) {
        UserActivity.launch(this, userName)
    }

    override fun onRefresh() {
        presenter?.getReps("")
    }

    override fun hideLoading() {
        swipe.post { swipe.isRefreshing = false }
    }

    override fun showLoading() {
        swipe.post { swipe.isRefreshing = true }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
