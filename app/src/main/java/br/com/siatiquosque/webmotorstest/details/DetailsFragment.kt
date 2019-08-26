package br.com.siatiquosque.webmotorstest.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.siatiquosque.webmotorstest.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_details.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject


class DetailsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsViewModel =
            ViewModelProviders.of(this, viewModelFactory)[DetailsViewModel::class.java]

        detailsViewModel.getDetails(arguments?.getInt("DETAILS") ?: 1)
            .observe(this, Observer {

                Glide.with(this)
                    .load(it.image)
                    .centerCrop()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                           startPostponedEnterTransition()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            startPostponedEnterTransition()

                            return false
                        }
                    })
                    .into(imgCar)

                tvCarName.text = "${it.make} ${it.model}"
                tvCarDesc.text = it.version
                tvValue.text = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                    .format(it.price.replace(",", ".").toDouble())
                tvYearValue.text = "${it.yearFab}/${it.yearModel}"
                tvKmValue.text = "${it.km}KM"
                tvColorValue.text = it.color

            })
    }


    fun ImageView.load(url: String, onLoadingFinished: () -> Unit = {}) {
        val listener = object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                onLoadingFinished()
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                onLoadingFinished()
                return false
            }
        }
        Glide.with(this)
            .load(url)
            .listener(listener)
            .into(this)
    }
}