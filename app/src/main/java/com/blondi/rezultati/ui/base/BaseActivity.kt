package com.blondi.rezultati.ui.base

import android.content.Context
import android.content.res.Configuration
import androidx.fragment.app.FragmentActivity
import com.blondi.rezultati.ui.widgets.LoadingDialog

abstract class BaseActivity : FragmentActivity() {
    private lateinit var progressDialogFragment: LoadingDialog


    open fun showLoadingDialog() {
        progressDialogFragment = LoadingDialog(this)
        progressDialogFragment.show()
    }

    open fun hideLoadingDialog() {
        if (progressDialogFragment != null) {
            progressDialogFragment.dismiss()
        }
    }

}