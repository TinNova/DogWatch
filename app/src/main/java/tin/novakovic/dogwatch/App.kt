package tin.novakovic.dogwatch

import android.app.Application
import tin.novakovic.dogwatch.di.AppComponent
import tin.novakovic.dogwatch.di.DaggerAppComponent

open class App : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
