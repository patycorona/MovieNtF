package com.example.myapppets.ui.petregister.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapppets.data.model.request.PetRequest
import com.example.myapppets.domian.model.ResultModel
import com.example.myapppets.domian.usecase.PetRegisterUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PetRegisterViewModelTest{

    private lateinit var petRegisterViewModel: PetRegisterViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var petRegisterUseCase: PetRegisterUseCase

    @Mock
    private lateinit var observer : androidx.lifecycle.Observer<ResultModel>

    private val petRequest = PetRequest("Drako","jugueton","labrador","Perruchis","123456978")
    private val petRequestError = PetRequest("Drako","jugueton","labrador","Perruchis","")

    private var resultModel = ResultModel()
    var resultModelError = ResultModel()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeViewModel()
        initObserver()
    }

    private fun initObjectMock() {
        resultModel = ResultModel(
            code = "0",
            message = "Sucessfull"
        )

        resultModelError = ResultModel(
            code = "-1",
            message = "Error"
        )
    }

    private fun initObserver()
    {
        petRegisterViewModel.petResult.observeForever(observer)
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(petRegisterUseCase.petRegister(any())).thenReturn(
            Single.just(
                resultModel
            )
        )
        whenever(petRegisterUseCase.petRegister(petRequestError)).thenReturn(
            Single.just(
                resultModelError
            )
        )
    }

    private fun initializeViewModel() {
        petRegisterViewModel = PetRegisterViewModel(
            petRegisterUseCase
        )
    }

    @Test
    fun `When call petRegister then execute one time petRegister `() {
        petRegisterViewModel.petRegister( "Drako","jugueton","labrador","Perruchis","123456978")
        verify(petRegisterUseCase, times(1)).petRegister(petRequest)
    }

    @Test
    fun `When call petRegister then return sucessfull response `() {
        petRegisterViewModel.petRegister( "Drako","jugueton","labrador","Perruchis","123456978")
        assertEquals(petRegisterViewModel.petResult.value?.code, "0")
        assertEquals(petRegisterViewModel.petResult.value?.message, "Sucessfull")
    }

    @Test
    fun `When call petRegister then return error response `() {
        petRegisterViewModel.petRegister( "Drako","jugueton","labrador","Perruchis","")
        assertEquals(petRegisterViewModel.petResult.value?.code, "-1")
    }

}

