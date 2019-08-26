package br.com.siatiquosque.webmotorstest.cars

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.siatiquosque.webmotorstest.R
import br.com.siatiquosque.webmotorstest.vo.Car
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

class CarsAdapter(val context: Context, val onClick: (Int, ImageView, TextView) -> Unit) :
    PagedListAdapter<Car, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_car_list, parent, false)
        return CarsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CarsViewHolder).bind(context, getItem(position), onClick)
    }

    class CarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.imgCar)
        private val value: TextView = view.findViewById(R.id.tvValue)
        private val name: TextView = view.findViewById(R.id.tvCarName)
        private val yearKM: TextView = view.findViewById(R.id.tvYearKm)
        private val root: CardView = view.findViewById(R.id.root)
        //        private val color: TextView = view.findViewById(R.id.tvColor)
        private var car: Car? = null

        fun bind(
            context: Context,
            car: Car?,
            onClick: (Int, ImageView, TextView) -> Unit
        ) {
            this.car = car
            Glide
                .with(context)
                .load(car?.image)
                .centerCrop()
                .into(image)
            value.text = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                .format(car?.price?.replace(",", ".")?.toDouble())
            name.text = car?.model
            yearKM.text = "${car?.yearModel}/${car?.yearFab} | ${car?.km}KM"
            root.setOnClickListener {
                onClick(car?.id ?: 0, image, value)
            }

        }

    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Car>() {

            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: Car, newItem: Car) =
                oldItem.id == newItem.id

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: Car, newItem: Car
            ) = oldItem == newItem
        }
    }

}