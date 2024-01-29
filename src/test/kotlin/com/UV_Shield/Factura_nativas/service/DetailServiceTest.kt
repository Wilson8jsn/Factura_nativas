package com.UV_Shield.Factura_nativas.service

import com.UV_Shield.Factura_nativas.model.Detail
import com.UV_Shield.Factura_nativas.repository.DetailRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.io.File

@ExtendWith(SpringExtension::class)
@SpringBootTest
class DetailServiceTest {

    @InjectMocks
    lateinit var detailService: DetailService

    @Mock
    lateinit var detailRepository: DetailRepository

    private val objectMapper = ObjectMapper()

    @Test
    fun saveDetailWithObject() {
        val newDetail = Detail().apply {
            id = 1
            quantity = 5
            price = 200
            invoice_Id = 1
            product_Id = 2
        }

        Mockito.`when`(detailRepository.save(Mockito.any(Detail::class.java))).thenReturn(newDetail)
        val savedDetail = detailService.save(newDetail)
        Assertions.assertEquals(newDetail, savedDetail)
    }

    @Test
    fun saveDetailWithJsonFile() {
        val jsonString = File("./src/test/resources/detail.json").readText(Charsets.UTF_8)
        val newDetail = objectMapper.readValue(jsonString, Detail::class.java)
        Mockito.`when`(detailRepository.save(Mockito.any(Detail::class.java))).thenReturn(newDetail)
        val savedDetail = detailService.save(newDetail)
        Assertions.assertEquals(newDetail, savedDetail)
    }
}
