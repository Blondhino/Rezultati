package com.blondi.rezultati.ui.widgets

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.blondi.rezultati.R

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.dialog_loading)

    }
}