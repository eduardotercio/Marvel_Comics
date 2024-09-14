package com.example.hqsmarvel

import android.app.Application
import com.example.comic.presentation.di.comicPresentationModule
import com.example.common.data.di.commonDataModule
import com.example.common.domain.di.commonDomainModule
import com.example.favorites.presentation.di.favoritesPresentationModule
import com.example.home.presentation.di.homePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(
                commonDataModule,
                commonDomainModule,
                homePresentationModule,
                comicPresentationModule,
                favoritesPresentationModule
            )
        }
    }
}