package com.example.myapppets.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.model.response.AllPetResponse
import com.example.myapppets.data.model.response.PetResponse
import com.example.myapppets.data.network.CoreHomeApi
import com.example.myapppets.domian.model.PetModel
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

class AllPetRepositoryImplTest{

    private lateinit var allPetRepositoryImpl: AllPetRepositoryImpl

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  apiService: CoreHomeApi

    private var allPetResponse = AllPetResponse()

    var list_pets = mutableListOf(
        PetResponse(123,
        "path",
        "Perro", "Labrador",
        "Perrito tipo calientito y peludito",
        "image_pet"),
        PetResponse(123,
            "path",
            "Perro", "Labrador",
            "Perrito tipo calientito y peludito",
            "image_pet")
    )

    private var petModel = mutableListOf(
        PetModel(123,
        "path",
        "Perro", "Labrador",
        "Perrito tipo calientito y peludito",
        "image_pet"),
        PetModel(123,
            "path",
            "Perro", "Labrador",
            "Perrito tipo calientito y peludito",
            "image_pet")
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeRepositoryImpl()
    }

    private fun initObjectMock() {

        allPetResponse = AllPetResponse(
            list_pets = list_pets
        )

    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(apiService.getAllPet()).thenReturn(
            Single.just(
                allPetResponse
            )
        )
    }

    private fun initializeRepositoryImpl() {
        allPetRepositoryImpl = AllPetRepositoryImpl(
            apiService
        )
    }

    @Test
    fun `When call userRegister then execute one time userAccess `() {
        allPetRepositoryImpl.getAllPet()
        verify(apiService, times(1)).getAllPet()
    }

    @Test
    fun `When call userRegister then return sucessfull response `() {
        allPetRepositoryImpl.getAllPet()
        assertEquals(petModel.size, 2)
    }
}