package com.UV_Shield.Factura_nativas.service

import com.UV_Shield.Factura_nativas.model.Product
import com.UV_Shield.Factura_nativas.repository.ProductRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    lateinit var productService: ProductService

    @Mock
    lateinit var productRepository: ProductRepository

    val jsonString = File("./src/test/resources/product.json").readText()
    val products = parseJsonToProductList(jsonString)

    val productMock = Product().apply {
        id = 1L
        description = "Example Product"
        brand = "Example Brand"
        price = 29.99
        stok = 100
    }

    @Test
    fun saveProductCorrect() {
        Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
        val response = productService.save(productMock)
        Assertions.assertEquals(response.id, productMock.id)
    }



    @Test
    fun listProducts() {

        Mockito.`when`(productRepository.findAll()).thenReturn(products)
        val resultList = productService.list()
        Assertions.assertEquals(products.size, resultList.size)
        Assertions.assertTrue(resultList.containsAll(products))
    }

    fun parseJsonToProductList(jsonString: String): List<Product> {
        val gson = Gson()
        val productType = object : TypeToken<List<Product>>() {}.type
        return gson.fromJson(jsonString, productType)
    }
}
