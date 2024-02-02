package com.UV_Shield.Factura_nativas.service

import com.UV_Shield.Factura_nativas.model.Client
import com.UV_Shield.Factura_nativas.repository.ClientRepository
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
class ClientServiceTest {

    @InjectMocks
    lateinit var clientService: ClientService

    @Mock
    lateinit var clientRepository: ClientRepository

    val jsonString = File("./src/test/resources/client.json").readText()
    val clients = parseJsonToClientList(jsonString)

    val clientMock = Client().apply {
        id = 1
        nui = "0301707030"
        full_name = "Juan"
        address = "Cuenca"
    }

    @Test
    fun saveClientCorrect() {
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.save(clientMock)
        Assertions.assertEquals(response.id, clientMock.id)
    }

    @Test
    fun saveClientWhenFull_nameIsBlank() {
        Assertions.assertThrows(Exception::class.java) {
            clientMock.apply { full_name = " " }
            Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
            clientService.save(clientMock)
        }
    }


    @Test
    fun listClients() {


        Mockito.`when`(clientRepository.findAll()).thenReturn(clients)

        val resultList = clientService.list()

        Assertions.assertEquals(clients.size, resultList.size)
        Assertions.assertTrue(resultList.containsAll(clients))
    }


    fun parseJsonToClientList(jsonString: String): List<Client> {
        val gson = Gson()
        val clientType = object : TypeToken<List<Client>>() {}.type
        return gson.fromJson(jsonString, clientType)


    }
}
