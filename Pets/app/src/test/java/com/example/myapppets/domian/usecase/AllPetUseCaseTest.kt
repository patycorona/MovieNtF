package com.example.myapppets.domian.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.data.model.response.AllPetResponse
import com.example.myapppets.data.model.response.PetResponse
import com.example.myapppets.data.repository.AllPetRepository
import com.example.myapppets.data.repository.UserAccessRepository
import com.example.myapppets.domian.model.PetModel
import com.example.myapppets.domian.model.PetResult
import com.example.myapppets.ui.home.viewmodel.PetViewModel
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

class AllPetUseCaseTest{
    private lateinit var allPetUseCase: AllPetUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var allPetRepository: AllPetRepository

    private var allPetResponse = AllPetResponse()
    var allPetResultError = PetResult()
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
        initializeUseCase()
    }

    private fun initObjectMock() {

        allPetResponse = AllPetResponse(
            list_pets = list_pets
        )

        allPetResultError = PetResult(
            sussess = false
        )
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(allPetRepository.getAllPet()).thenReturn(
            Single.just(
                petModel
            )
        )
    }

    private fun initializeUseCase() {
        allPetUseCase = AllPetUseCase(
            allPetRepository
        )
    }

    @Test
    fun `When call allPet then execute one time allPet `() {
        allPetUseCase.getAllPet()
        verify(allPetRepository, times(1)).getAllPet()
    }

    @Test
    fun `When call allPet then return sucessfull response `() {
        allPetUseCase.getAllPet()
        assertEquals(petModel.size, 2)
    }

}