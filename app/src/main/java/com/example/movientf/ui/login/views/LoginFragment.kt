package com.example.movientf.ui.login.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movientf.R
import com.example.movientf.databinding.FragmentLoginBinding
import com.example.movientf.domain.model.ConstantGeneral.Companion.CODE_OK
import com.example.movientf.domain.model.LoginResultModel
import com.example.movientf.domain.model.UserModel
import com.example.movientf.ui.MainActivity
import com.example.movientf.ui.component.Screen
import com.example.movientf.ui.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    var binding: FragmentLoginBinding? = null
    private val loginViewModel: LoginViewModel by viewModels()
    var userModel: UserModel? = UserModel()
    var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(LayoutInflater.from(context), null, false)

        initListener()
        initObserver()
        return binding?.root
    }

    private fun initListener() {
        binding?.apply {

            btnIniciarSesion.setOnClickListener {
                if (!edEmail.text.isNullOrEmpty() && !edPwd.text.isNullOrBlank()) {
                    validaUser(edEmail.text.toString(), edPwd.text.toString())
                }
                else{
                    Toast.makeText(requireContext(),R.string.msg_is_null_or_empty.toString() , Toast.LENGTH_SHORT).show()
                }
            }
            tvSuscribete.setOnClickListener {
                (activity as MainActivity)
                    .changeScreen(Screen.UserRegisterFragment.toString())
            }

            tvNameApp.setOnClickListener{
                (activity as MainActivity)
                    .changeScreen(Screen.LoginFragment.toString())

            }
        }
    }

    private var loginResultObserver = Observer<LoginResultModel> { loginResultM ->
        if (loginResultM.code == CODE_OK) {

            CoroutineScope(Dispatchers.Default).launch {
                loginViewModel.setPrefDatStore(loginResultM.user.name + " "+
                        loginResultM.user.last_name, loginResultM.token)
            }

            userModel = loginResultM.user
            token = loginResultM.token

            Toast.makeText(requireContext(), R.string.lbl_bienvenido, Toast.LENGTH_SHORT).show()

            (activity as MainActivity)
                .changeScreen(Screen.HomeProfileFragment.toString(), token)
        } else {
            Toast.makeText(requireContext(), R.string.msg_error, Toast.LENGTH_LONG).show()
        }
    }

    private fun validaUser(email: String, pwd :String) {
        loginViewModel.login(email, pwd)
    }

    private fun initObserver() {
        loginViewModel.loginResultModel.observe(viewLifecycleOwner, loginResultObserver)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}