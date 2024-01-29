package com.UV_Shield.Factura_nativas.service

import com.UV_Shield.Factura_nativas.model.Client
import com.UV_Shield.Factura_nativas.model.Product
import com.UV_Shield.Factura_nativas.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    lateinit var productService: ProductService

    @Mock
    lateinit var productRepository: ProductRepository

    @Test
    fun saveProduct() {
        val productMock = Product().apply {
            id = 1
            description = "Marca stock"
            brand = "Marca"
            price = 100.0
            stok = 50
        }

        Mockito.`when`(productRepository.existsById(productMock.id!!)).thenReturn(false)
        Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
        val savedProduct = productService.save(productMock)
        Assertions.assertEquals(productMock, savedProduct)
    }
}
