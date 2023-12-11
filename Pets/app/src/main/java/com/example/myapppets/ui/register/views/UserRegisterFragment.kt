package com.example.myapppets.ui.register.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapppets.R
import com.example.myapppets.databinding.FragmentUserRegisterBinding
import com.example.myapppets.domian.model.ResultModel
import com.example.myapppets.ui.home.views.MainActivity
import com.example.myapppets.domian.model.Screen
import com.example.myapppets.ui.register.viewmodel.UserRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRegisterFragment : Fragment() {

    var binding: FragmentUserRegisterBinding? = null
    private val userRegisterViewModel: UserRegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRegisterBinding.inflate(
            LayoutInflater.from(context), null, false
        )

        initUserRegisterObserver()
        initListener()
        return binding?.root
    }

    private fun initListener() {
        binding?.ivRegresar?.setOnClickListener {
            (activity as MainActivity)
                .changeScreen(Screen.LoginFragment)
        }

        binding?.btnUserRegister?.setOnClickListener {
            userRegister(
                binding?.edFirstName?.text.toString(),
                binding?.edLastName?.text.toString(),
                binding?.edEmail?.text.toString(),
                binding?.edPwd?.text.toString()
            )
        }
    }

    private fun initUserRegisterObserver() {
        userRegisterViewModel.userResultModel.observe(viewLifecycleOwner, userRegisterResult)
    }

    private val userRegisterResult = Observer<ResultModel> { resultModel ->
        if (resultModel.code == CODE) {
            Toast.makeText(requireContext(), R.string.msg_success_register, Toast.LENGTH_SHORT).show()
            (activity as MainActivity)
                .changeScreen(Screen.LoginFragment)
        } else {
            Toast.makeText(requireContext(),R.string.msg_error_register, Toast.LENGTH_SHORT).show()
        }
    }

    private fun userRegister(name: String, lastName: String, email: String, password: String) {
        userRegisterViewModel.userRegister(name, lastName, email, password)
    }

    companion object {

        const val CODE = "0"

        @JvmStatic
        fun newInstance() =
            UserRegisterFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}