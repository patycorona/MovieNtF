package com.example.myfavoritewebsites.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfavoritewebsites.R
import com.example.myfavoritewebsites.models.url.AddUrlResponseModel

class UrlAdapter(
    private val dataSource: MutableList<String>
) : RecyclerView.Adapter<UrlAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameurl: TextView = view.findViewById(R.id.tv_url) as TextView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_item_url_recently, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameurl.text = dataSource[position].toString()
    }

    override fun getItemCount() = dataSource.size
}
