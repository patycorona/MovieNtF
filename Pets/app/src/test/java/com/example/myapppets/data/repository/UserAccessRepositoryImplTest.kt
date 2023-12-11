package com.example.myapppets.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.model.response.UserAccessResponse
import com.example.myapppets.data.network.CoreHomeApi
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

class UserAccessRepositoryImplTest {

    private lateinit var userAccessRepositoryImpl: UserAccessRepositoryImpl

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  apiService: CoreHomeApi

    private val userRequest = UserAccessRequest(email = "test@admin.com", password = "Password123")
    private val userRequestError = UserAccessRequest(email = "test@admin.com", password = "")

    private var resultModel = ResultModel()
    var resultModelError = ResultModel()

    private lateinit var userAccessResponse: UserAccessResponse
    private lateinit var userAccessResponseError: UserAccessResponse

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
    }

    private fun initObjectMock() {
         userAccessResponse = UserAccessResponse(
            code = "0",
            message = "Sucessfull",
            user = UserRequest(
                name = "Juan",
                lastName = "Perez"
            )
        )

         userAccessResponseError = UserAccessResponse(
            code = "-1",
            message = "Error",
            user = UserRequest(
                name = "",
                lastName = ""
            )
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
        whenever(apiService.userAccess(userRequest)).thenReturn(
            Single.just(
                userAccessResponse
            )
        )
        whenever(apiService.userAccess(userRequestError)).thenReturn(
            Single.just(
                userAccessResponseError
            )
        )
    }

    private fun initializeViewModel() {
        userAccessRepositoryImpl = UserAccessRepositoryImpl(
            apiService
        )
    }

    @Test
    fun `When call userAccess then execute one time userAccess `() {
        initializeViewModel()
        val userAccessRequest = UserAccessRequest("test@admin.com", "Password123")
        userAccessRepositoryImpl.userAccess(userAccessRequest)
        verify(apiService, times(1)).userAccess(userRequest)
    }

    @Test
    fun `When call userAccess then return sucessfull response `() {
        initializeViewModel()
        val userAccessRequest = UserAccessRequest("test@admin.com", "Password123")
        userAccessRepositoryImpl.userAccess(userAccessRequest)
        assertEquals(resultModel.code, "0")
        assertEquals(resultModel.message, "Sucessfull")
    }

    @Test
    fun `When call userAccess then return error response `() {
        initializeViewModel()
        val userAccessRequest = UserAccessRequest("test@admin.com", "")
        userAccessRepositoryImpl.userAccess(userAccessRequest)
        assertEquals(resultModelError.code, "-1")
    }

}