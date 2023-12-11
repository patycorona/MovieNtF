package com.example.myapppets.domian.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.repository.UserAccessRepository
import com.example.myapppets.data.repository.UserRegisterRepository
import com.example.myapppets.domian.model.ResultModel
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

class UserRegisterUseCaseTest{

    private lateinit var userRegisterUseCase: UserRegisterUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRegisterRepository: UserRegisterRepository

    private val userRequest = UserRequest("Prue","Unitarian", "test@admin.com", "Password123")
    private val userRequestError = UserRequest(name = "Prue", lastName = "Unitarian", email = "test@admin.com", password = "")

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
        whenever(userRegisterRepository.userRegister(userRequest)).thenReturn(
            Single.just(
                resultModel
            )
        )
        whenever(userRegisterRepository.userRegister(userRequestError)).thenReturn(
            Single.just(
                resultModelError
            )
        )
    }

    private fun initialize_useCase() {
        userRegisterUseCase = UserRegisterUseCase(
            userRegisterRepository
        )
    }

    @Test
    fun `When call userRegister then execute one time userAccess `() {
        val userRequest = UserRequest("Pepito","Clavo","test@admin.com", "Password123")
        userRegisterUseCase.userRegister(userRequest)
        verify(userRegisterRepository, times(1)).userRegister(userRequest)
    }

    @Test
    fun `When call userRegister then return sucessfull response `() {
        val userRegisterRequest = UserRequest("Pepito","Clavo","test@admin.com", "Password123")
        userRegisterUseCase.userRegister(userRegisterRequest)
        assertEquals(resultModel.code, "0")
        assertEquals(resultModel.message, "Sucessfull")
    }

    @Test
    fun `When call userRegister then return error response `() {
        val userRegisterRequest = UserRequest("Pepito","Clavo", "test@admin.com", "")
        userRegisterUseCase.userRegister(userRegisterRequest)
        assertEquals(resultModelError.code, "-1")
    }

}