package com.example.myapppets.ui.login.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.myapppets.databinding.FragmentLoginBinding
import com.example.myapppets.domian.model.ResultModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer
import com.example.myapppets.R
import com.example.myapppets.domian.model.Screen
import com.example.myapppets.ui.home.views.MainActivity
import com.example.myapppets.ui.login.viewmodel.UserAccessViewModel

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val userAccessViewModel: UserAccessViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            LayoutInflater.from(context), null, false
        )

        initListener()
        initLoginObserver()

        return binding?.root
    }

    private fun initListener() {
        binding?.btnEnter?.setOnClickListener {
            validUser(
                binding?.edUserName?.text.toString(),
                binding?.edPassword?.text.toString()
            )
        }

        binding?.tvRegistro?.setOnClickListener {
            (activity as MainActivity)
                .changeScreen(Screen.UserRegisterFragment)
        }
        binding?.tvEmailVisitante?.setOnClickListener {
            (activity as MainActivity)
                .changeScreen(Screen.MainVisit)
        }

    }

    private val userAccessResultObserver = Observer<ResultModel> { accessResultModel ->
        if (accessResultModel.code == CODE) {
            (activity as MainActivity)
                .changeScreen(Screen.HomeFragment)
        } else {

            Toast.makeText(
                requireContext(),
                R.string.msg_password_invalid,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initLoginObserver() {
        userAccessViewModel.userResultModel.observe(viewLifecycleOwner, userAccessResultObserver)
    }

    private fun validUser(user: String, pwd: String) {
        userAccessViewModel.userValidation(user, pwd)
    }

    companion object {

        var binding: FragmentLoginBinding? = null
        private val CODE = "0"

        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}