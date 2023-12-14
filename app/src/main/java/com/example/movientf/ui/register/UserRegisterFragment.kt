package com.example.movientf.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movientf.R
import com.example.movientf.databinding.FragmentUserRegisterBinding
import com.example.movientf.domain.model.ConstantGeneral
import com.example.movientf.domain.model.ResultModel
import com.example.movientf.ui.MainActivity
import com.example.movientf.ui.component.Screen
import com.example.movientf.ui.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRegisterFragment : Fragment() {

    var binding: FragmentUserRegisterBinding? = null
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserRegisterBinding.inflate(LayoutInflater.from(context), null, false)

        initListener()
        initObserver()
        return binding?. root
    }

    private fun initListener(){

        binding?.apply {

            btnRegistrar?.setOnClickListener {
                if(!edUserName.text.toString().isNullOrEmpty()) {
                    visibleControls()

                    sendMail(edUserName?.text.toString())
                } else{
                    Toast.makeText(requireContext(), R.string.msg_error_email_pwd, Toast.LENGTH_LONG).show()
                }
            }

            tvCerrar?.setOnClickListener {
                (activity as MainActivity)
                    .changeScreen(Screen.PresentationActivity.toString())
            }
        }
    }

    private var ResultObserver = Observer<ResultModel> { resultModel ->
        if (resultModel.code == ConstantGeneral.CODE_OK) {
            visibleControls()
        } else {
            Toast.makeText(requireContext(), R.string.msg_error, Toast.LENGTH_LONG).show()
        }
    }

    private fun visibleControls(){
        binding?.apply {
            btnRegistrar.visibility = View.GONE
            tvMessage.text = getText(R.string.msg_send_email)
        }
    }

    private fun sendMail(email : String){
        loginViewModel.sendEmail(email)
    }

    private fun initObserver() {
        loginViewModel.resultModel.observe(viewLifecycleOwner, ResultObserver)
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