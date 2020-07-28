package tin.novakovic.dogwatch.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dog.view.*
import tin.novakovic.core.domain_layer.Dog
import tin.novakovic.dogwatch.R
import tin.novakovic.dogwatch.ui.main.MainAdapter.*

class MainAdapter(private val itemClickListener: MainClickListener) :
    RecyclerView.Adapter<DogViewHolder>() {

    private var dogs: List<Dog> = listOf()

    fun setData(data: List<Dog>) {
        dogs = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dogs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder =
        DogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false))

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(dogs[position], itemClickListener)

    }

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            dog: Dog,
            itemClickListener: MainClickListener
        ) {

            val dogBreed = dog.subBreed + dog.breed
            itemView.title_item_dog.text = dogBreed

            itemView.setOnClickListener {
                itemClickListener.onDogClicked(dog)
            }
        }

    }


}