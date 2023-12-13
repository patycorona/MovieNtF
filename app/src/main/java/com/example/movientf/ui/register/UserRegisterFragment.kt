package com.example.movientf.ui.register

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movientf.R
import com.example.movientf.databinding.FragmentUserRegisterBinding

@AndroidEntryPoint
class UserRegisterFragment : Fragment() {

    var binding: FragmentUserRegisterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserRegisterBinding.inflate(LayoutInflater.from(context), null, false)
        // Inflate the layout for this fragment
        return binding?. root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            UserRegisterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}