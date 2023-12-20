package com.example.smartbiz

import com.example.smartbiz.response.Login

object DataDummy {

    fun generateDummyLoginResponse(): Login {
        return Login(
            password = "password",
            username = "username"

        )

    }
}