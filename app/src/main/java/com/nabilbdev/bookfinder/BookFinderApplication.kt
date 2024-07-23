package com.nabilbdev.bookfinder

import android.app.Application
import com.nabilbdev.bookfinder.data.repository.AppContainer
import com.nabilbdev.bookfinder.data.repository.DefaultAppContainer

class BookFinderApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}