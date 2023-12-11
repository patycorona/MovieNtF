package com.example.myapppets.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapppets.R
import com.example.myapppets.databinding.FragmentDetHomeBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetDetBottomSheet (
    private val name: String,
    private val raza: String,
    private val image: String
) : BottomSheetDialogFragment() {
    private var binding: FragmentDetHomeBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = true
        setupHeight()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        binding = FragmentDetHomeBinding.inflate(
            LayoutInflater.from(requireContext()),
            null,
            false
        )

        configBottomSheet()
        binding?.tvNamePet?.text = name
        binding?.tvPetDet?.text = raza

        Glide.with(this)
            .load(image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding!!.imgPet)

        binding?.ivRegresar?.setOnClickListener {
            dismiss()
        }

        return binding?.root
    }

    private fun configBottomSheet() {
        binding?.root?.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.bg_bottom_sheet
        )
    }

    private fun setupHeight() {
        val displayMetrics = activity?.resources?.displayMetrics
        val height = displayMetrics?.heightPixels
            ?: resources.getDimension(R.dimen.modal_height)
                .toInt()
        val maxHeight = height * HEIGHT

        binding?.root?.viewTreeObserver?.addOnGlobalLayoutListener {
            binding?.containerDet?.setLayoutHeight(maxHeight.toFloat())
        }

        binding?.root?.post {
            val params =
                (binding?.root?.parent as View).layoutParams as CoordinatorLayout.LayoutParams

            params.behavior?.let { behavior ->
                if (behavior is BottomSheetBehavior<*>) {
                    behavior.peekHeight = maxHeight.toInt()
                    behavior.skipCollapsed = true
                }
            }
        }
    }

    override fun getTheme(): Int = R.style.CustomSheetDialog

    companion object {
        private const val HEIGHT = .90

        @JvmStatic
        fun newInstance(
            title: String,
            news: String,
            image: String

        ): PetDetBottomSheet {
            return PetDetBottomSheet(title, news, image)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}