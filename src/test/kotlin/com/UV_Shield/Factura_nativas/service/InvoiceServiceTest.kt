package com.UV_Shield.Factura_nativas.service


import com.UV_Shield.Factura_nativas.model.Client
import com.UV_Shield.Factura_nativas.model.Invoice
import com.UV_Shield.Factura_nativas.repository.ClientRepository
import com.UV_Shield.Factura_nativas.repository.InvoiceRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.util.*

@SpringBootTest
class InvoiceServiceTest {

    @InjectMocks
    lateinit var invoiceService: InvoiceService

    @Mock
    lateinit var invoiceRepository: InvoiceRepository

    @Mock
    lateinit var clientRepository: ClientRepository

    val jsonString = File("./src/test/resources/invoice.json").readText(Charsets.UTF_8)
    val invoiceMock = Gson().fromJson(jsonString, Invoice::class.java)
    val jsonStringList = File("./src/test/resources/invoice_list.json").readText(Charsets.UTF_8)
    val invoices = Gson().fromJson(jsonStringList, Array<Invoice>::class.java).toList()

    val clientMock = Client().apply {
        id = 1
        nui = "0301707030"
        full_name = "Juan"
        address = "Ceunca"
    }

    @Test
    fun saveInvoiceWhenIsCorrect() {
        Mockito.`when`(clientRepository.findById(invoiceMock.client_Id)).thenReturn(clientMock)
        Mockito.`when`(invoiceRepository.save(Mockito.any(Invoice::class.java))).thenReturn(invoiceMock)
        val response = invoiceService.save(invoiceMock)
        Assertions.assertEquals(response.id, invoiceMock.id)
    }

    @Test
    fun listInvoices() {
        Mockito.`when`(invoiceRepository.findAll()).thenReturn(invoices)
        val resultList = invoiceService.list()
        Assertions.assertEquals(invoices.size, resultList.size)
        Assertions.assertTrue(resultList.containsAll(invoices))
    }
}
