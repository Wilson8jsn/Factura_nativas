package com.UV_Shield.Factura_nativas.controller



import com.UV_Shield.Factura_nativas.model.Invoice
import com.UV_Shield.Factura_nativas.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/invoice")   //endpoint
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class InvoiceController {
    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping
    fun list(): List<Invoice> {
        return invoiceService.list()
    }

    @PostMapping
    fun save(@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.save(invoice), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.update(invoice), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.updateName(invoice), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long): Boolean? {
        return invoiceService.delete(id)
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(invoiceService.listById(id), HttpStatus.OK)

    }

}