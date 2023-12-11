package com.example.myapppets.domian.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapppets.data.model.request.PetRequest
import com.example.myapppets.data.repository.PetRegisterRepository
import com.example.myapppets.domian.model.ResultModel
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

class PetRegisterUseCaseTest{
    private lateinit var petRegisterUseCase: PetRegisterUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var petRegisterRepository: PetRegisterRepository

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
        initialize_useCase()
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

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(petRegisterRepository.petRegister(petRequest)).thenReturn(
            Single.just(
                resultModel
            )
        )
        whenever(petRegisterRepository.petRegister(petRequestError)).thenReturn(
            Single.just(
                resultModelError
            )
        )
    }

    private fun initialize_useCase() {
        petRegisterUseCase = PetRegisterUseCase(
            petRegisterRepository
        )
    }

    @Test
    fun `When call petRegister then execute one time petRegister `() {
        val petRequest = PetRequest("Drako","jugueton","labrador","Perruchis", "123456978")
        petRegisterUseCase.petRegister(petRequest)
        verify(petRegisterRepository, times(1)).petRegister(petRequest)
    }

    @Test
    fun `When call petRegister then return sucessfull response `() {
        val userRegisterRequest = PetRequest("Drako","jugueton","labrador","Perruchis", "123456978")
        petRegisterUseCase.petRegister(userRegisterRequest)
        assertEquals(resultModel.code, "0")
        assertEquals(resultModel.message, "Sucessfull")
    }

    @Test
    fun `When call userRegister then return error response `() {
        val petRegisterRequest = PetRequest("Pepito","Clavo", "test@admin.com", "")
        petRegisterUseCase.petRegister(petRegisterRequest)
        assertEquals(resultModelError.code, "-1")
    }

}