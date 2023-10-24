package com.istudio.code.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import com.istudio.code.data.ShoppingCartCacheFake
import com.istudio.code.generators.productGenerator
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ShoppingCartTest {

    private lateinit var cart: ShoppingCart
    private lateinit var shoppingCartCacheFake: ShoppingCartCacheFake

    @BeforeEach
    fun setUp() {
        shoppingCartCacheFake = ShoppingCartCacheFake()
        cart = ShoppingCart(shoppingCartCacheFake)
    }

    @Test
    fun `Test products are saved in cache`() {
        // --> GIVEN
        val product = Product(id = 1, name = "chocolate" , price = 5.0)

        // --> ACTION
        cart.addProduct(product, quantity = 2)

        // --> ASSERTION
        val productsFromCache = shoppingCartCacheFake.loadCart()
        assertThat(productsFromCache).hasSize(2)
        assertThat(productsFromCache).contains(product)


        // Observe only we modify just one param and not all the params
        //val person1 = productGenerator().copy(id = 1)
        //val person2 = productGenerator().copy(id = 2)


    }


}