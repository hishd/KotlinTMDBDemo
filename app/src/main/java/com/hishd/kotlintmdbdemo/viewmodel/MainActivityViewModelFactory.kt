package com.hishd.kotlintmdbdemo.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import java.lang.ref.WeakReference

class MainActivityViewModelFactory(val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(context = WeakReference(context)) as T
        }

        throw IllegalArgumentException("Could not find a matching ViewModel")
    }
}