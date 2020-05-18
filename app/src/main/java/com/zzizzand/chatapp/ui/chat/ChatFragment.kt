package com.zzizzand.chatapp.ui.chat

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zzizzand.chatapp.R
import kotlinx.android.synthetic.main.fragment_chat.view.*


class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_chat, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val discs = mutableListOf<Map<String, String>>()
        for (i in 0..30) discs.add(
            mapOf(
                "name" to getString(R.string.sample_name),
                "disc" to getString(R.string.sample_message)
            )
        )
        view.disc_recycler_view.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = DiscussionAdapter(discs)

        }


    }



}
