package com.example.myapppets.ui.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapppets.data.model.response.AllPetResponse
import com.example.myapppets.data.model.response.PetResponse
import com.example.myapppets.domian.model.PetModel
import com.example.myapppets.domian.model.PetResult
import com.example.myapppets.domian.usecase.AllPetUseCase
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

class PetViewModelTest{
    private lateinit var petViewModel:PetViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var allPetUseCase: AllPetUseCase

    @Mock
    private lateinit var observer: Observer<PetResult>

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

    private var petModel = mutableListOf(PetModel(123,
        "path",
        "Perro", "Labrador",
        "Perrito tipo calientito y peludito",
        "image_pet"),
        PetModel(123,
            "path",
            "Perro", "Labrador",
            "Perrito tipo calientito y peludito",
            "image_pet"))

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

        allPetResponse = AllPetResponse(
            list_pets = list_pets
        )
    }

    private fun initObserver()
    {
        petViewModel.listPetsRs.observeForever(observer)
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(allPetUseCase.getAllPet()).thenReturn(
            Single.just(
                petModel
            )
        )
    }

    private fun initializeViewModel() {
        petViewModel = PetViewModel(
            allPetUseCase
        )
    }

    @Test
    fun `When call allPets then execute one time allPets `() {
        petViewModel.getPets()
        verify(allPetUseCase, times(1)).getAllPet()
    }

    @Test
    fun `When call allPets then return sucessfull response `() {
        petViewModel.getPets()
        assertEquals(petViewModel.listPetsRs.value?.list?.size, 2)
    }

}