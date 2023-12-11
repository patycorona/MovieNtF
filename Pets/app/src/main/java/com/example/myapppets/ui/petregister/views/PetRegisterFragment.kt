package com.example.myapppets.ui.petregister.views

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapppets.R
import com.example.myapppets.databinding.FragmentPetRegisterBinding
import com.example.myapppets.domian.model.ResultModel
import com.example.myapppets.domian.model.Screen
import com.example.myapppets.ui.component.toBase64
import com.example.myapppets.ui.home.views.MainActivity
import com.example.myapppets.ui.petregister.viewmodel.PetRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetRegisterFragment : Fragment() {

    var binding: FragmentPetRegisterBinding ? = null
    private val petRegisterViewModel: PetRegisterViewModel by viewModels()
    var permissionCount = 0
    var photoBase64: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetRegisterBinding.inflate(
            LayoutInflater.from(context), null, false
        )

        binding?.ivAddImgPet?.setOnClickListener {
            if (permissionCount > 0) {
                issueCameraIntent()
            }
        }

        binding?.ivRegresar?.setOnClickListener {
            (activity as MainActivity)
                .changeScreen(Screen.HomeFragment)
        }

        iniCam()
        initPetRegisterObserver()
        initListener()
        return binding?.root
    }

    private fun initListener() {
        binding?.btnSave?.setOnClickListener {
            petRegister(
                binding?.edName?.text.toString(),
                binding?.edType?.text.toString(),
                binding?.edRaza?.text.toString(),
                binding?.edObs?.text.toString(),
                photoBase64.toString()
            )
        }
    }

    private fun petRegister(name: String, type: String, raza: String, obs: String, image: String) {
        petRegisterViewModel.petRegister(name,type,raza,obs,image)
    }

    private fun initPetRegisterObserver() {
        petRegisterViewModel.petResult.observe(viewLifecycleOwner, petRegisterResult)
    }

    private val petRegisterResult = Observer<ResultModel> { resultModel ->
        if (resultModel.code == CODE) {
            Toast.makeText(requireContext(), R.string.msg_success_register, Toast.LENGTH_SHORT).show()
            (activity as MainActivity)
                .changeScreen(Screen.HomeFragment)
        } else {
            Toast.makeText(requireContext(),R.string.msg_error_user , Toast.LENGTH_SHORT).show()
        }
    }

    private fun issueCameraIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, ID_ACT_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ID_ACT_CAMERA && resultCode == Activity.RESULT_OK) {

            val b: Bitmap? = data?.extras?.getParcelable(DATA)
            photoBase64 = b?.toBase64()

            binding?.addImage?.setImageBitmap(b)
        }
    }

    private fun iniCam() {

        permissionCount = 0

        if (PermissionChecker.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PermissionChecker.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION_CAMERA)
        } else {
            permissionCount++
        }

        if (PermissionChecker.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSON_WRITE_EXTERNAL_STORAGE
            )
        } else {
            permissionCount++
        }
    }

    companion object {


        const val REQUEST_PERMISSION_CAMERA = 1001
        const val REQUEST_PERMISSON_WRITE_EXTERNAL_STORAGE = 1002
        const val ID_ACT_CAMERA = 20001
        const val DATA = "data"
        const val CODE = "0"

        @JvmStatic
        fun newInstance() =
            PetRegisterFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}