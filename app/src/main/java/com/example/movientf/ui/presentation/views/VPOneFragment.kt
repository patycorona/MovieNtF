package com.example.movientf.ui.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.movientf.R
import com.example.movientf.databinding.FragmentVpOneBinding
import com.example.movientf.domain.model.ConstantGeneral.Companion.ARG_OBJECT
import com.example.movientf.domain.model.ConstantGeneral.Companion.CODE_OK
import com.example.movientf.ui.MainActivity
import com.example.movientf.ui.component.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VPOneFragment : Fragment() {

    var binding : FragmentVpOneBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVpOneBinding.inflate(LayoutInflater.from(context),null, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT)}?.apply {

            binding?.tvTitle?.text = CODE_OK + getInt(ARG_OBJECT).toString()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            VPOneFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}