package br.com.siatiquosque.webmotorstest.cars

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.siatiquosque.webmotorstest.R
import br.com.siatiquosque.webmotorstest.util.Status
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

        val adapter = CarsAdapter(requireContext()) { id, image, value ->
            val extras = FragmentNavigatorExtras(
                image to "imageCar",
                value to "valueCar"
            )
            findNavController().navigate(
                R.id.action_carsFragment_to_detailsFragment,
                bundleOf(Pair("DETAILS", id)),
                null,
                extras
            )
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