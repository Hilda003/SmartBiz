package com.example.smartbiz

import com.example.smartbiz.response.Payload
import com.example.smartbiz.response.ResponseLogin

object DataDummy {

    fun generateDummyLoginResponse(): ResponseLogin {
        return ResponseLogin(
            Payload(
                "username"
            ),
            true

        )

    }
}