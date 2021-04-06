package com.example.nike.features.teams

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.nike.R
import com.example.nike.core.extensions.getViewModelFactory
import com.example.nike.core.platform.CustomLayoutManager
import com.example.nike.databinding.FragmentSearchTeamsBinding
import com.google.android.material.snackbar.Snackbar

class SearchTeamsFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentSearchTeamsBinding
    private lateinit var teamsAdapter: TeamsAdapter
    private val searchTeamsViewModel by viewModels<TeamsViewModel> { getViewModelFactory()  }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentSearchTeamsBinding.inflate(inflater, container, false).apply {
            viewmodel = searchTeamsViewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewDataBinding.teamList.layoutManager = CustomLayoutManager(this.requireContext())
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListAdapter()
        setupSnackbar()
        //arguments?.get(TEAM_NAME)?.also { searchTeamsViewModel.searchTeamByName( it as String) }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_team_menu, menu)
        attachSearchListener(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.searchTeam -> {
                true
            }else ->{
                false
            }
        }
    }

    private fun attachSearchListener(menu: Menu)=
        menu.findItem(R.id.searchTeam).also {
            val searchView = it.actionView as SearchView
            searchView.queryHint = resources.getString(R.string.search_hint)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.clearFocus()
                    searchView.setQuery("", false)
                    searchTeamsViewModel.searchTeamByName(query)
                    return true
                }

                override fun onQueryTextChange(text: String): Boolean {
                    if (text.isEmpty()) {
                        // do something
                    }
                    return false
                }
            })
        }

    private fun setupListAdapter(){
        val viewModel: TeamsViewModel? = viewDataBinding.viewmodel
        if (viewModel != null){
            teamsAdapter = TeamsAdapter(viewModel)
            viewDataBinding.teamList.adapter = teamsAdapter
            val spacing = viewDataBinding.teamList.resources.getDimensionPixelSize(R.dimen.carousel_spacing)
            viewDataBinding.teamList.addItemDecoration(LinerHorizonItemDecoration(spacing))
            PagerSnapHelper().attachToRecyclerView(viewDataBinding.teamList)
        }

    }

    private fun setupSnackbar() {
        searchTeamsViewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            view?.also { v -> Snackbar.make( v, it, Snackbar.LENGTH_SHORT).show() }
        })

    }


    companion object {
        private const val TEAM_NAME = "teamName"

        @JvmStatic
        fun newInstance() =
            SearchTeamsFragment().apply {
                arguments = Bundle().apply {
                    putString(TEAM_NAME, "")
                }
            }

    }
}