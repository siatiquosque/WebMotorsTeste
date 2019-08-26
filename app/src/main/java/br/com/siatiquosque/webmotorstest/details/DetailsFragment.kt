package br.com.siatiquosque.webmotorstest.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.siatiquosque.webmotorstest.R
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsViewModel =
            ViewModelProviders.of(this, viewModelFactory)[DetailsViewModel::class.java]

        detailsViewModel.getDetails(arguments?.getInt("DETAILS") ?: 1)
            .observe(this, Observer {
                Glide
                    .with(this)
                    .load(it?.image)
                    .centerCrop()
                    .into(imgCar)

                tvCarName.text = "${it.make} ${it.model}"
                tvCarDesc.text = it.version
                tvValue.text = it.price
                tvYearValue.text = "${it.yearFab}/${it.yearModel}"
                tvKmValue.text = it.km.toString()
                tvColorValue.text = it.color

            })
    }
}