package com.example.myapppets.ui.home.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapppets.databinding.FragmentHomeBinding
import com.example.myapppets.domian.model.PetModel
import com.example.myapppets.domian.model.PetResult
import com.example.myapppets.ui.component.PetDetBottomSheet
import com.example.myapppets.ui.home.viewmodel.PetViewModel
import com.example.myapppets.ui.home.views.Adapter.PetAdapter
import com.example.myapppets.ui.tools.ShareResourses
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    var binding: FragmentHomeBinding? = null
    private val petViewModel: PetViewModel by viewModels()

    private val listPetsObserver = Observer<PetResult> { petsResult ->
        if (petsResult.sussess) {
            petsResult.list?.let {
                val adapter = PetAdapter(
                    it,
                    onItemClickListener,
                    requireContext(),
                    onItemClickToShare
                )
                binding?.recyclerview?.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(
            LayoutInflater.from(context), null, false
        )

        initRecycler()
        initObserver()
        petViewModel.getPets()
        return binding?.root
    }

    private fun initObserver() {
        petViewModel.listPetsRs.observe(viewLifecycleOwner, listPetsObserver)
    }

    private var onItemClickToShare: ((petModel: PetModel) -> Unit) = { petModel ->
        startActivity(
            Intent.createChooser(
                ShareResourses().shareTextPlain(
                    petModel.name,
                    TYPE_SHARE
                ), this.toString()
            )
        )
    }

    private var onItemClickListener: ((petModel: PetModel) -> Unit) = { petModel ->
        var description = petModel.type + "," + "\n" + petModel.raza + "," + "\n" + petModel.obs
        PetDetBottomSheet.newInstance(
            petModel.name,
            description,
            petModel.image
        )
            .show(requireActivity().supportFragmentManager, "")
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerview?.apply {
            layoutManager = linearLayoutManager
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

    companion object {
        const val TYPE_SHARE = "text/plain"

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}