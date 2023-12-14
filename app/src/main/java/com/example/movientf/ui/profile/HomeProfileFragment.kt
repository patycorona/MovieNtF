package com.example.movientf.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movientf.R
import com.example.movientf.databinding.FragmentHomeProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeProfileFragment : Fragment() {

    var binding: FragmentHomeProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeProfileBinding.inflate(LayoutInflater.from(context), null ,false)
        return binding?.root
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            HomeProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}