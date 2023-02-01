package com.onurmert.retro4fitt.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.onurmert.retro4fitt.Adapter.CurrentRecyclerAdapter
import com.onurmert.retro4fitt.Model.FilmsModel
import com.onurmert.retro4fitt.Utils.InternetControl
import com.onurmert.retro4fitt.ViewModel.CurrentViewModel
import com.onurmert.retro4fitt.databinding.FragmentCurrentBinding

class CurrentFragment : Fragment() {

    private lateinit var binding: FragmentCurrentBinding

    private lateinit var viewModel: CurrentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CurrentViewModel::class.java)
        viewModel.getMovieViewModel(requireContext())

        val internetControl = InternetControl(requireContext(), binding.currentFragmentView)
        internetControl.internetSnackMessage(internetControl.connection())
    }

    override fun onResume() {
        super.onResume()
        getAll()
    }

    private fun getAll(){
        viewModel.filmList.observe(this, Observer {
                item ->
            createRecycler(item)
        })
    }

    private fun createRecycler(filmList : ArrayList<FilmsModel>){
        filmList.reverse()
        binding.recyclerCurrentView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCurrentView.adapter = CurrentRecyclerAdapter(filmList)
    }
}