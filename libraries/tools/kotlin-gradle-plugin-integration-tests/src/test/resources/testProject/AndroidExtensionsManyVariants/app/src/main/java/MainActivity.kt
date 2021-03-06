package org.example.manyvariants

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_in_library.text_view
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewMain
        text_view
    }
}

@Parcelize
class User(val firstName: String, val lastName: String) : Parcelable