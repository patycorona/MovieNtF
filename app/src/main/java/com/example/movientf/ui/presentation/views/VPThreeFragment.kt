package com.example.movientf.ui.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.layout.Layout
import com.example.movientf.R
import com.example.movientf.databinding.FragmentVPThreeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VPThreeFragment : Fragment() {

    var binding : FragmentVPThreeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVPThreeBinding.inflate(LayoutInflater.from(context), null, false)
        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            VPThreeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}