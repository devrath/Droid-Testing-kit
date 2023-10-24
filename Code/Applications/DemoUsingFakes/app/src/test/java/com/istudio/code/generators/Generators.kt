package com.istudio.code.generators

import com.istudio.code.domain.Product

fun productGenerator() : Product {
    return Product(
        id = 1,
        name = "Current product",
        price = 10.0
    )
}