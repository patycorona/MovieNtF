package com.example.myapppets.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.model.response.UserAccessResponse
import com.example.myapppets.data.model.response.UserResult
import com.example.myapppets.data.network.CoreHomeApi
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

class UserRegisterRepositoryImplTest{
    private lateinit var userRegisterRepositoryImpl: UserRegisterRepositoryImpl

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  apiService: CoreHomeApi

    private val userRequest = UserRequest("Prue","Unitarian", "test@admin.com", "Password123")
    private val userRequestError = UserRequest("Prue","Unitarian", "test@admin.com", "")

    private var resultModel = ResultModel()
    var resultModelError = ResultModel()

    private lateinit var userResponse: UserResult
    private lateinit var userResponseError: UserResult

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeRepositoryImpl()
    }

    private fun initObjectMock() {
        userResponse = UserResult(
            code = "0",
            message = "Sucessfull"
        )

        userResponseError = UserResult(
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
        whenever(apiService.userRegister(userRequest)).thenReturn(
            Single.just(
                userResponse
            )
        )
        whenever(apiService.userRegister(userRequestError)).thenReturn(
            Single.just(
                userResponseError
            )
        )

    }

    private fun initializeRepositoryImpl() {
        userRegisterRepositoryImpl = UserRegisterRepositoryImpl(
            apiService
        )
    }

    @Test
    fun `When call userRegister then execute one time userAccess `() {
        val userRegisterRequest = UserRequest("Prue","Unitarian", "test@admin.com", "Password123")
        userRegisterRepositoryImpl.userRegister(userRegisterRequest)
        verify(apiService, times(1)).userRegister(userRequest)
    }

    @Test
    fun `When call userRegister then return sucessfull response `() {
        val userRegisterRequest = UserRequest("Prue","Unitarian", "test@admin.com", "Password123")
        userRegisterRepositoryImpl.userRegister(userRegisterRequest)
        assertEquals(resultModel.code, "0")
        assertEquals(resultModel.message, "Sucessfull")
    }

    @Test
    fun `When call userRegister then return error response `() {
        val userRegisterRequest = UserRequest("Prue","Unitarian", "test@admin.com", "")
        userRegisterRepositoryImpl.userRegister(userRegisterRequest)
        assertEquals(resultModelError.code, "-1")
    }

}