package com.example.myapppets.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapppets.data.model.request.PetRequest
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.model.response.PetRegisterResult
import com.example.myapppets.data.model.response.UserResult
import com.example.myapppets.data.network.CoreHomeApi
import com.example.myapppets.domian.model.ResultModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PetRegisterRepositoryImplTest{
    private lateinit var petRegisterRepositoryImpl: PetRegisterRepositoryImpl

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  apiService: CoreHomeApi

    private val petRequest = PetRequest("Drako","jugueton","labrador","Perruchis","123456978")
    private val petRequestError = PetRequest("Drako","jugueton","labrador","Perruchis","")

    private var resultModel = ResultModel()
    var resultModelError = ResultModel()

    private var petResponse = PetRegisterResult()
    private lateinit var petResponseError: PetRegisterResult

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeRepositoryImpl()
    }

    private fun initObjectMock() {
        petResponse = PetRegisterResult(
            code = "0",
            message = "Sucessfull"
        )

        petResponseError = PetRegisterResult(
            code = "-1",
            message = "Error"
        )

        resultModel = ResultModel(
            code = "0",
            message = "Sucessfull"
        )

        resultModelError = ResultModel(
            code = "-1",
            message = "Error"
        )
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(apiService.petRegister(petRequest)).thenReturn(
            Single.just(
                petResponse
            )
        )
        whenever(apiService.petRegister(petRequestError)).thenReturn(
            Single.just(
                petResponseError
            )
        )
    }


    private fun initializeRepositoryImpl() {
        petRegisterRepositoryImpl = PetRegisterRepositoryImpl(
            apiService
        )
    }

    @Test
    fun `When call petRegister then execute one time petRegister `() {
        val petRegisterRequest = PetRequest("Drako","jugueton","labrador","Perruchis","123456978")
        petRegisterRepositoryImpl.petRegister(petRegisterRequest)
        verify(apiService, times(1)).petRegister(petRequest)
    }

    @Test
    fun `When call userRegister then return sucessfull response `() {
        val petRegisterRequest = PetRequest("Drako","jugueton","labrador","Perruchis","123456978")
        petRegisterRepositoryImpl.petRegister(petRegisterRequest)
        assertEquals(resultModel.code, "0")
        assertEquals(resultModel.message, "Sucessfull")
    }

    @Test
    fun `When call userRegister then return error response `() {
        val petRegisterRequest = PetRequest("Drako","jugueton","labrador","Perruchis","")
        petRegisterRepositoryImpl.petRegister(petRegisterRequest)
        assertEquals(resultModelError.code, "-1")
    }

}