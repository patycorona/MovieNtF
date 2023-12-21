package com.example.movientf.data.repository

import com.example.movientf.R
import com.example.movientf.data.model.response.AllProfileResponse
import com.example.movientf.data.model.response.LoginResponse
import com.example.movientf.data.model.response.ProfileResponse
import com.example.movientf.data.model.response.ResultResponse
import com.example.movientf.data.model.response.UserResponse
import com.example.movientf.domain.model.ProfileModel
import io.reactivex.Single

class MockCoreServiceApi {

    fun mockLogin() = Single.just(
        LoginResponse(
            code = "0",
            message = "successfull",
            userResponse = UserResponse(
                id_client = "001",
                email = "juan9923@hotmail.com",
                name = "Juan",
                last_name = "López",
                birthday = "",
                password = "",
                address = "",
                phone = ""
            ),
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwdWJsaWNfaWQiOiJhY2FkNWQ4Yy00MDg1LTQ0NmUtYmEzNy1kOTExMTk0NjM4NmYiLCJleHAiOjE3MDIzNDIxNzd9.z00IU0qLY7s-iwcgja6zDNE10Un7b8gdP6903M-aEws"
        )
    )

    fun MockdeleteProfile() = Single.just(
        ResultResponse(
            code = "0",
            message = "successfull",
        )
    )

    fun getProfilebyClient(): Single<AllProfileResponse> {
        return Single.just(AllProfileResponse(getlist()))
    }

    fun updateProfile() = Single.just(
        ResultResponse(
            code = "0",
            message = "successfull",
        )
    )

    fun mockSendEmail() = Single.just(
        ResultResponse(
            code = "0",
            message = "successfull",
        )
    )

    fun getlist(): MutableList<ProfileResponse>{
        val allProfileListByUser = mutableListOf<ProfileResponse>()
        allProfileListByUser.add(mockGetProfileModel1)
        allProfileListByUser.add(mockGetProfileModel2)
        allProfileListByUser.add(mockGetProfileModel3)
        return allProfileListByUser
    }

    companion object{
      val mockGetProfileModel1 =
          ProfileResponse(
        id = "A-001",
        user_name = "usuario",
        image =  R.drawable.profile1.toString()
        )
        val mockGetProfileModel2 =
            ProfileResponse(
                id = "A-002",
                user_name = "usuario",
                image =  R.drawable.profile2.toString()
            )
        val mockGetProfileModel3 =
            ProfileResponse(
                id = "A-003",
                user_name = "usuario",
                image =  R.drawable.profile3.toString()
            )
    }

}