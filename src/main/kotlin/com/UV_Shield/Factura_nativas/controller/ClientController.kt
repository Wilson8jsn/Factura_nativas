package com.UV_Shield.Factura_nativas.controller




import com.UV_Shield.Factura_nativas.model.Client
import com.UV_Shield.Factura_nativas.service.ClientService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/client")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class ClientController {
    @Autowired
    lateinit var clientService: ClientService

    @GetMapping
    fun list(): List<Client> {
        return clientService.list()
    }

    @PostMapping
    fun save(@RequestBody client: Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.save(client), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody client: Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.update(client), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody client: Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.updateName(client), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long): Boolean? {
        return clientService.delete(id)
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(clientService.listById(id), HttpStatus.OK)

    }

    private val log: Logger = LoggerFactory.getLogger(Client::class.java)

    @GetMapping("/client/address/{address}")
    fun getClientsWithAddress(@PathVariable address: String?): ResponseEntity<List<Client>> {
        log.info("Received request for address: {}", address)
        val clients = clientService.getClientsWithAddress(address)
        log.info("Found {} clients", clients.size)
        return ResponseEntity(clients, HttpStatus.OK)
    }

    /*
    @GetMapping
    fun list(pageable: Pageable): ResponseEntity<Page<Client>> {
        val response = clientService.list(pageable)
        return ResponseEntity(response, HttpStatus.OK)
    }
    */
}