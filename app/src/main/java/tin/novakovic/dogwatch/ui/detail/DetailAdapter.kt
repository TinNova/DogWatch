package tin.novakovic.dogwatch.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_dog_image.view.*
import tin.novakovic.dogwatch.R

class DetailAdapter :
    RecyclerView.Adapter<DetailAdapter.DogImageViewHolder>() {

    private var imageUrls: List<String> = listOf()

    fun setData(data: List<String>) {
        imageUrls = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = imageUrls.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder =
        DogImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_dog_image, parent, false)
        )

    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        holder.bind(imageUrls[position])

    }

    class DogImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            imageUrl: String
        ) {
            Picasso.get()
                .load(imageUrl)
//                .placeholder(R.drawable.ic_movie_wheel)
                .into(itemView.image_dog)
        }

    }
}