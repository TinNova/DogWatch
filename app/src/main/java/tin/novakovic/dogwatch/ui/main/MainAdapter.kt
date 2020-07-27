package tin.novakovic.dogwatch.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dog.view.*
import tin.novakovic.dogwatch.R
import tin.novakovic.dogwatch.api.Dog
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
            itemView.title_item_dog.text =
                    // this needs to be tested, so it's better to add a space after subBreed if not null in the rxChain
                if (dog.subBreed.isNullOrEmpty()) dog.breed else "${dog.subBreed} ${dog.breed}"

            itemView.setOnClickListener {
                itemClickListener.onDogClicked(dog)
            }
        }

    }


}