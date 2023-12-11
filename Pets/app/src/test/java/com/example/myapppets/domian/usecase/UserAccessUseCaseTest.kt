package com.example.myapppets.domian.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.data.repository.UserAccessRepository
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

class UserAccessUseCaseTest {

    private lateinit var useCase: UserAccessUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userAccessRepository: UserAccessRepository

    private val userRequest = UserAccessRequest(email = "test@admin.com", password = "Password123")
    private val userRequestError = UserAccessRequest(email = "test@admin.com", password = "")

    private var resultModel = ResultModel()
    var resultModelError = ResultModel()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeViewModel()
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
        whenever(userAccessRepository.userAccess(userRequest)).thenReturn(
            Single.just(
                resultModel
            )
        )
        whenever(userAccessRepository.userAccess(userRequestError)).thenReturn(
            Single.just(
                resultModelError
            )
        )
    }

    private fun initializeViewModel() {
        useCase = UserAccessUseCase(
            userAccessRepository
        )
    }

    @Test
    fun `When call userAccess then execute one time userAccess `() {

        val userAccessRequest = UserAccessRequest("test@admin.com", "Password123")
        useCase.userAccess(userAccessRequest)
        verify(userAccessRepository, times(1)).userAccess(userRequest)
    }

    @Test
    fun `When call userAccess then return sucessfull response `() {
        val userAccessRequest = UserAccessRequest("test@admin.com", "Password123")
        useCase.userAccess(userAccessRequest)
        assertEquals(resultModel.code, "0")
        assertEquals(resultModel.message, "Sucessfull")
    }

    @Test
    fun `When call userAccess then return error response `() {
        val userAccessRequest = UserAccessRequest("test@admin.com", "")
        useCase.userAccess(userAccessRequest)
        assertEquals(resultModelError.code, "-1")
    }


}