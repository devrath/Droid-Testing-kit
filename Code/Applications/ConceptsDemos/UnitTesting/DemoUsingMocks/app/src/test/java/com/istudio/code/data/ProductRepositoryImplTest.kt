package com.istudio.code.data

import assertk.assertThat
import assertk.assertions.isTrue
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
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.HttpException

class ProductRepositoryImplTest {

    // Define the SUT: System under test :-> In our case it is repository
    private lateinit var sut : ProductRepositoryImpl

    private lateinit var api: ProductApi
    private lateinit var logger: AnalyticsLogger


    @BeforeEach
    fun setup(){
        api = mockk()
        logger = mockk(relaxed = true)
        sut = ProductRepositoryImpl(productApi = api, analyticsLogger = logger)
    }

    @Test
    fun `When there is Response Error for any input , Exception is logged`() = runBlocking {
        // Call the API and make it to generate exception mentioned whenever the api is used with any input
        coEvery {
            api.purchaseProducts(any())
        } throws mockk<HttpException>(){
            // <---- This describes how this Http Mock should behave ---->
            // On the exception, The code() block should return 404 error
            every { code() } returns 404
            // On the exception, THe message() block should return
            every { message() } returns "Test Message"
        }

        // Now the result must return specific error based on what we assert
        val result = sut.purchaseProducts(listOf())

        // Check if the failure is true
        assertThat(result.isFailure).isTrue()

        // Check if the logger event was triggered when error had occured
        verify {

            logger.logEvent(
                "http_error",
                LogParam("code", 404),
                LogParam("message", "Test Message")
            )
        }

    }


    @Test
    fun `When there is Response Error for particular input , Exception is logged`() = runBlocking{
        // THe api will throw the exception mentioned  when its being called with specific input
        coEvery {
            api.purchaseProducts(
                ProductsDto(
                    listOf(
                        Product(id = 1, name = "Hello", 5.0)
                    )
                )
            )
        } throws mockk<HttpException>(){
            // <---- This describes how this Http Mock should behave ---->
            // On the exception, The code() block should return 404 error
            every { code() } returns 404
            // On the exception, THe message() block should return
            every { message() } returns "Test Message"
        }

        // Now the result must return specific error based on what we assert
        val result = sut.purchaseProducts(listOf(
            Product(id = 1, name = "Hello", 5.0)
        ))

        // Check if the failure is true
        assertThat(result.isFailure).isTrue()

        // Check if the logger event was triggered when error had occured
        verify {

            logger.logEvent(
                "http_error",
                LogParam("code", 404),
                LogParam("message", "Test Message")
            )
        }

    }



    @Test
    fun `Mock object that you don't have access to , Exception is logged`() = runBlocking {
        // Call the API and make it to generate exception mentioned whenever the api is used with any input
        coEvery {
            api.purchaseProducts(any())
        } throws mockk<HttpException>(){
            // <---- This describes how this Http Mock should behave ---->
            // On the exception, The code() block should return 404 error
            every { code() } returns 404
            // On the exception, THe message() block should return
            every { message() } returns "Test Message"
        }

        // Observe the output instead of `power rangers` the output `thunder cats` is printed
        mockkConstructor(CustomProduct::class)
        every { anyConstructed<CustomProduct>().name  } returns "Thunder cats"

        // Now the result must return specific error based on what we assert
        val result = sut.purchaseProducts(listOf())

        // Check if the failure is true
        assertThat(result.isFailure).isTrue()

        // Check if the logger event was triggered when error had occured
        verify {

            logger.logEvent(
                "http_error",
                LogParam("code", 404),
                LogParam("message", "Test Message")
            )
        }

    }


}