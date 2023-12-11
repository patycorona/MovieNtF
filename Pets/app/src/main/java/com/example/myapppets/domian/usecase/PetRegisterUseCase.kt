package com.example.myapppets.domian.usecase

import com.example.myapppets.data.model.request.PetRequest
import com.example.myapppets.data.repository.PetRegisterRepository
import com.example.myapppets.domian.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class PetRegisterUseCase @Inject constructor (
    var petRegisterRepository: PetRegisterRepository
    ) {
    fun petRegister(petRequest: PetRequest): Single<ResultModel> =
        petRegisterRepository.petRegister(petRequest)


}