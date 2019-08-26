package br.com.siatiquosque.webmotorstest.cars

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.siatiquosque.webmotorstest.R
import br.com.siatiquosque.webmotorstest.util.NetworkState
import br.com.siatiquosque.webmotorstest.util.Status
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cars.*
import javax.inject.Inject

class CarsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carsViewModel = ViewModelProviders.of(this, viewModelFactory)[CarsViewModel::class.java]

        val adapter = CarsAdapter(requireContext()) {
            findNavController().navigate(R.id.detailsFragment, bundleOf(Pair("DETAILS", it)))
        }

        rvCars.adapter = adapter
        rvCars.layoutManager = LinearLayoutManager(activity)

        carsViewModel.carsResult.observe(this, Observer {
            adapter.submitList(it)
        })

        carsViewModel.networkState?.observe(this, Observer {
            swCars.isRefreshing = it.status == Status.RUNNING
        })



        swCars.setOnRefreshListener {
            carsViewModel.invalidationDataSource()
            swCars.isRefreshing = false
        }
    }

}