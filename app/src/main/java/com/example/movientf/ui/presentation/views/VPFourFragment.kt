package com.example.movientf.ui.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movientf.databinding.FragmentVPFourBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VPFourFragment : Fragment() {

    var binding: FragmentVPFourBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVPFourBinding.inflate(LayoutInflater.from(context), null ,false)
        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            VPFourFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}