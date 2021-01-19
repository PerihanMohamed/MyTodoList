package com.example.mytodolistkotlin.utils

import android.net.sip.SipSession
import androidx.appcompat.widget.SearchView
import java.util.*

inline fun SearchView.onQueryTextChange (crossinline listener : (String) ->Unit ) {

    this.setOnQueryTextListener( object :  SearchView.OnQueryTextListener {

        override fun onQueryTextChange(newText: String): Boolean {
            return true
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            listener(query.orEmpty())
            return true
        }

    })


}