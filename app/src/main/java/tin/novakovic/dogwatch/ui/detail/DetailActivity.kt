package tin.novakovic.dogwatch.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*
import tin.novakovic.dogwatch.*
import tin.novakovic.dogwatch.api.Dog
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DOG_KEY = "dog_key"
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<DetailViewModel>
    private lateinit var viewModel: DetailViewModel
    private val adapter = DetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        intent.extras?.let {
            viewModel.onInitView(it.get(DOG_KEY) as Dog)
        }

        initView()
    }

    private fun initView() {
        observeViewState()
        rv_detail_activity.adapter = adapter
        rv_detail_activity.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
    }

    private fun observeViewState() {
        viewModel.viewState.observe(this, Observer {
            it?.let {
                when (it.isPresenting) {
                    true -> {
                        adapter.setData(it.imageUrls)
                        rv_detail_activity.visible()
                        loading_icon_detail_activity.gone()
                        network_detail_activity.gone()
                    }
                }
                when (it.isError) {
                    true -> {
                        rv_detail_activity.gone()
                        loading_icon_detail_activity.gone()
                        network_detail_activity.visible()
                    }
                }
                when (it.isLoading) {
                    true -> {
                        rv_detail_activity.gone()
                        loading_icon_detail_activity.visible()
                        network_detail_activity.gone()
                    }
                }
            }
        })
    }
}
