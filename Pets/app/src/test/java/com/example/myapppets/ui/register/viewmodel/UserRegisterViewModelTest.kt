package com.example.myapppets.ui.register.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.domian.model.ResultModel
import com.example.myapppets.domian.usecase.UserRegisterUseCase
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

class UserRegisterViewModelTest{

    private lateinit var userRegisterViewModel: UserRegisterViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRegisterUseCase: UserRegisterUseCase

    @Mock
    private lateinit var observe : Observer<ResultModel>

    private val userRequest = UserRequest(name = "Prue", lastName = "Unitarian", email = "test@admin.com", password = "Password123")
    private val userRequestError = UserRequest(name = "Prue", lastName = "Unitarian", email = "test@admin.com", password = "")

    private var resultModel = ResultModel()
    var resultModelError = ResultModel()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeViewModel()
        iniObserver()
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
    private fun iniObserver(){
        userRegisterViewModel.userResultModel.observeForever(observe)
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(userRegisterUseCase.userRegister(userRequest)).thenReturn(
            Single.just(
                resultModel
            )
        )
        whenever(userRegisterUseCase.userRegister(userRequestError)).thenReturn(
            Single.just(
                resultModelError
            )
        )
    }

    private fun initializeViewModel() {
        userRegisterViewModel = UserRegisterViewModel(
            userRegisterUseCase
        )
    }

    @Test
    fun `When call userRegister then execute one time userAccess `() {
        userRegisterViewModel.userRegister( "Prue","Unitarian","test@admin.com", "Password123")
        verify(userRegisterUseCase, times(1)).userRegister(userRequest)
    }

    @Test
    fun `When call userRegister then return sucessfull response `() {
        userRegisterViewModel.userRegister( "Prue","Unitarian","test@admin.com", "Password123")
        assertEquals(userRegisterViewModel.userResultModel.value?.code, "0")
        assertEquals(userRegisterViewModel.userResultModel.value?.message, "Sucessfull")
    }

    @Test
    fun `When call userRegister then return error response `() {
        userRegisterViewModel.userRegister( "Prue","Unitarian","test@admin.com","")
        assertEquals(userRegisterViewModel.userResultModel.value?.code, "-1")
    }

}