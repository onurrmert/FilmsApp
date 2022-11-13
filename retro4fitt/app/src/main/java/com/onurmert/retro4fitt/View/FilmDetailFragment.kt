package com.onurmert.retro4fitt.View

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.onurmert.retro4fitt.Model.FilmsModel
import com.onurmert.retro4fitt.R
import com.onurmert.retro4fitt.Utils.InternetControl
import com.onurmert.retro4fitt.ViewModel.FilmDetailViewModel
import com.onurmert.retro4fitt.databinding.FragmentFilmDetailBinding
import com.squareup.picasso.Picasso

class FilmDetailFragment : Fragment() {

    private lateinit var binding: FragmentFilmDetailBinding

    private lateinit var viewModel: FilmDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FilmDetailViewModel::class.java)
        viewModel.getOnlyFilm(requireContext(), getID())

        val internetControl = InternetControl(requireContext(), binding.filmDetailFragment)
        internetControl.internetSnackMessage(internetControl.connection())
    }

    override fun onResume() {
        super.onResume()
        getfilm()
    }

    private fun getfilm(){
        viewModel.filmModel.observe(this, Observer {
            item ->
            filmInit(item)
        })
    }
    private fun filmInit(filmsModel: FilmsModel){

        Picasso.with(requireContext())
            .load(filmsModel.large_cover_image)
            .error(R.drawable.ic_launcher_background)
            .into(binding.filmDetailsImageView)

        binding.titleText.text = filmsModel.title
        binding.ratingText.text = filmsModel.rating
        binding.summaryText.text = filmsModel.summary
        binding.yearText.text = filmsModel.year

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, filmsModel.urlFilm)
            startActivity(intent)
        }
    }
    private fun getID() : Int{
        val bundle = arguments
        val args = FilmDetailFragmentArgs.fromBundle(bundle!!)
        return args.id
    }
}