package com.example.movientf.ui.profile.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movientf.databinding.FragmentProfileItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileItemFragment : Fragment() {

    var binding : FragmentProfileItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileItemBinding.inflate(LayoutInflater.from(context), null, false)
        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileItemFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}