package com.example.smartbiz.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.smartbiz.DataDummy
import com.example.smartbiz.response.ResponseLogin
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.example.smartbiz.data.Result
import com.example.smartbiz.getOrAwaitValue
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var smartbiz : Repository
    private lateinit var loginViewModel: LoginViewModel
    private val loginDummy = DataDummy.generateDummyLoginResponse()

    @Test
    fun `when Login Should Not Null and Return Data` () {
        val expectedLogin = MutableLiveData<Result<ResponseLogin>>()
        expectedLogin.value = Result.Success(loginDummy)
        Mockito.`when`(smartbiz.postLogin(email = "email", password = "password"))
            .thenReturn(expectedLogin)
        loginViewModel = LoginViewModel(smartbiz)
        val actualLogin =
            loginViewModel.login(email = "email", password = "password").getOrAwaitValue()
        Mockito.verify(smartbiz).postLogin(email = "email", password = "password")
        assertNotNull(actualLogin)
        assertEquals(expectedLogin.value, actualLogin)

    }

}