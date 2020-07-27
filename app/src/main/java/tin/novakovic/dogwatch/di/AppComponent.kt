package tin.novakovic.dogwatch.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tin.novakovic.dogwatch.ui.main.MainActivity
import tin.novakovic.dogwatch.di.modules.ApiModule
import tin.novakovic.dogwatch.di.modules.AppModule
import tin.novakovic.dogwatch.ui.detail.DetailActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)

}