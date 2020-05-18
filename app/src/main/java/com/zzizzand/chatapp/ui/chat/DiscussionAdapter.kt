package com.zzizzand.chatapp.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zzizzand.chatapp.R
import kotlinx.android.synthetic.main.discussion_item.view.*

class DiscussionAdapter(private val discussions: List<Map<String, String>>) :
    RecyclerView.Adapter<DiscussionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.name
        val disc: TextView = view.message_snippet
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.discussion_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = discussions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val disc = discussions[position]
        holder.name.text = disc["name"]
        holder.disc.text = disc["disc"]
    }

}