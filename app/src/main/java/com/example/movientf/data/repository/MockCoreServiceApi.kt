package com.example.movientf.data.repository

import com.example.movientf.data.model.request.UserRequest
import com.example.movientf.data.model.response.LoginResponse
import io.reactivex.Single

class MockCoreServiceApi {

    fun mocklogin() = Single.just(
        LoginResponse(
            code = "0",
            message = "successfull",
            userRequest = UserRequest(
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
}