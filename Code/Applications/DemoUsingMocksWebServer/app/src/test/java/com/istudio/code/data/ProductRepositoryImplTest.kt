package com.istudio.code.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.google.gson.Gson
import com.istudio.code.domain.AnalyticsLogger
import com.istudio.code.domain.CustomProduct
import com.istudio.code.domain.LogParam
import com.istudio.code.domain.Product
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ProductRepositoryImplTest {

    // Define the SUT: System under test :-> In our case it is repository
    private lateinit var sut : ProductRepositoryImpl

    private lateinit var api: ProductApi
    private lateinit var logger: AnalyticsLogger

    private lateinit var mockWebServer: MockWebServer


    @BeforeEach
    fun setup(){
        mockWebServer = MockWebServer()
        // Build the retroft instance and pass a mockServer instead of real api
        api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create()

        logger = mockk(relaxed = true)
        sut = ProductRepositoryImpl(productApi = api, analyticsLogger = logger)
    }

    @Test
    fun `Response error, Exception logged - Using Mock server`() = runBlocking {
        // We can enqueue the
        mockWebServer.enqueue(
            MockResponse().setResponseCode(404)
        )

        // make the call to the API
        val result = sut.purchaseProducts(listOf())

        assertThat(result.isFailure).isEqualTo(true)
    }


}