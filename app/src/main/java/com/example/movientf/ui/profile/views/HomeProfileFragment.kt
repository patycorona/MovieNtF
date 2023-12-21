package com.example.movientf.ui.profile.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movientf.R
import com.example.movientf.databinding.FragmentHomeProfileBinding
import com.example.movientf.domain.model.ConstantGeneral
import com.example.movientf.domain.model.ConstantGeneral.Companion.ADD_PROFILE
import com.example.movientf.domain.model.ConstantGeneral.Companion.GET_PROFILE
import com.example.movientf.domain.model.ConstantGeneral.Companion.HOME
import com.example.movientf.domain.model.ConstantGeneral.Companion.TWO
import com.example.movientf.domain.model.ProfileModel
import com.example.movientf.domain.model.ProfileResult
import com.example.movientf.domain.model.ResultModel
import com.example.movientf.ui.profile.viewmodel.ProfileViewModel
import com.example.movientf.ui.profile.views.adapter.ProfileAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeProfileFragment (
    private var token : String?
    ): Fragment() {

    var binding: FragmentHomeProfileBinding? = null
    private val profileViewModel: ProfileViewModel by viewModels()
    private var process:String = ""
    private var idProduct: String = ""


    private val listProfileObserver = Observer<ProfileResult> { profileResult ->
        if (profileResult.sussess) {
            profileResult.list_profile?.let {
                val adapter = ProfileAdapter(
                    it,
                    onItemClickListener,
                    requireContext()
                )
                binding?.recyclerview?.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }

    var onItemClickListener: ((profileModel: ProfileModel, type:String) -> Unit) = { profileModel, type ->
        idProduct = profileModel.id
        process = type

        if(type == HOME )
            Toast.makeText(requireContext(), "Bienvenido a HOME ${profileModel.user_name}", Toast.LENGTH_LONG)
                .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeProfileBinding.inflate(
            LayoutInflater.from(context), null ,false)

        initRecycler()
        initObserver()
        initListener()
        return binding?.root
    }

    private fun initListener(){

    }

    private fun initRecycler() {
        val linearLayoutManager =  GridLayoutManager(requireContext(), TWO)
        binding?.recyclerview?.apply {
            layoutManager = linearLayoutManager
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

    private var ProfileResultObserver = Observer<ResultModel> { ResModel ->
        if (ResModel.code == ConstantGeneral.CODE_OK) {
            when(process){
                HOME -> {
                    Toast.makeText(requireContext(), "Selecciona una peli", Toast.LENGTH_LONG)
                        .show()
                }
                GET_PROFILE -> {
                    Toast.makeText(requireContext(), "Estos son todos los perfiles", Toast.LENGTH_LONG).show()
                }
                ADD_PROFILE -> {
                    Toast.makeText(requireContext(), R.string.msg_success, Toast.LENGTH_LONG).show()
                }
            }

            //Cuando se Registre correctamente debera mostrarse en el grid
        }else{
            Toast.makeText(requireContext(), R.string.msg_error, Toast.LENGTH_LONG).show()
        }
    }

    private fun addProfile(name:String, image: String){
        profileViewModel.addProfile(name, image)
    }

    private fun initObserver() {
        profileViewModel.resultModel.observe(viewLifecycleOwner, ProfileResultObserver)
        profileViewModel.listProfileRM.observe(viewLifecycleOwner, listProfileObserver)
   }

    companion object {
        @JvmStatic
        fun newInstance(token : String?) : HomeProfileFragment {
            return HomeProfileFragment(token)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}