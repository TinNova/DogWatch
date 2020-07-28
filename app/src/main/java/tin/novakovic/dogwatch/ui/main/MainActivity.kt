package tin.novakovic.dogwatch.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tin.novakovic.core.domain_layer.Dog
import tin.novakovic.dogwatch.*
import tin.novakovic.dogwatch.ui.detail.DetailActivity
import tin.novakovic.dogwatch.ui.detail.DetailActivity.Companion.DOG_KEY
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    MainClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var viewModel: MainViewModel

    private val adapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.onInitView()
        initView()
    }

    private fun initView() {
        observeViewState()
        rv_main_activity.adapter = adapter
        rv_main_activity.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
    }

    private fun observeViewState() {
        viewModel.viewState.observe(this, Observer {
            it?.let {
                when (it.isPresenting) {
                    true -> {
                        adapter.setData(it.dogs)
                        rv_main_activity.visible()
                        loading_icon_main_activity.gone()
                        network_main_activity.gone()
                    }
                }
                when (it.isError) {
                    true -> {
                        rv_main_activity.gone()
                        loading_icon_main_activity.gone()
                        network_main_activity.visible()
                    }
                }
                when (it.isLoading) {
                    true -> {
                        rv_main_activity.gone()
                        loading_icon_main_activity.visible()
                        network_main_activity.gone()
                    }
                }
            }
        })
    }

    override fun onDogClicked(dog: Dog) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra(DOG_KEY, dog)
        )
    }
}

// Add a recyclerView decorator to add margins and line spacer between items
// Remove the login in the Viewholder in the MainAdapter