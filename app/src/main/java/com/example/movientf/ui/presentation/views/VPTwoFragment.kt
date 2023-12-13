package com.example.movientf.ui.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movientf.databinding.FragmentVPTwoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VPTwoFragment : Fragment() {

    var binding: FragmentVPTwoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVPTwoBinding.inflate(LayoutInflater.from(context), null, false)
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            VPTwoFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}