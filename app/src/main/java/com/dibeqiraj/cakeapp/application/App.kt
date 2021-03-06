package com.dibeqiraj.cakeapp.application

import android.app.Application
import com.dibeqiraj.cakeapp.di.components.ApplicationComponent
import com.dibeqiraj.cakeapp.di.components.DaggerApplicationComponent
import com.dibeqiraj.cakeapp.di.module.ApplicationModule
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig

class App : Application() {

    val mApplicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this, "https://gist.githubusercontent.com"))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        initializeFresco()
    }

    fun initializeFresco() {
        val config: ImagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build()
        Fresco.initialize(this, config)
    }

}