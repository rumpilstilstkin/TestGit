package com.example.rumpi.testgit.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.example.rumpi.testgit.R
import com.example.rumpi.testgit.models.RepModel

interface RepCallbacks {
    fun onRepClick(userName: String)
}

class RepAdapter(
        val context: Context,
        val callback: RepCallbacks
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var data: ArrayList<RepModel> = ArrayList()

    fun setItems(items: List<RepModel>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): RepModel = data[position]

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
            RepItemViewHolder.create(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = getItem(position)
        (holder as RepItemViewHolder).bind(model, callback)

    }
}

class RepItemViewHolder private constructor(root: View) : RecyclerView.ViewHolder(root) {
    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup) =
            RepItemViewHolder(inflater.inflate(R.layout.item_rep, parent, false))
    }

    private val name by bindView<TextView>(R.id.ir_tv_name)
    private val language by bindView<TextView>(R.id.ir_tv_language)

    private var callbacks: RepCallbacks? = null
    private var model: RepModel? = null

    private val listener = View.OnClickListener {
        model?.owner?.login?.let {
            callbacks?.onRepClick(it)
        }
    }

    init {
        root.setOnClickListener(listener)
    }

    fun bind(model: RepModel, callbacks: RepCallbacks) {
        this.callbacks = callbacks
        this.model = model
        name.text = model.name
        language.text = model.language
    }
}