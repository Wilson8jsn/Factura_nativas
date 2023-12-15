package com.UV_Shield.Factura_nativas.service



import com.UV_Shield.Factura_nativas.model.Detail
import com.UV_Shield.Factura_nativas.repository.DetailRepository
import com.UV_Shield.Factura_nativas.repository.InvoiceRepository
import com.UV_Shield.Factura_nativas.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var detailRepository: DetailRepository

    fun list ():List<Detail>{
        return detailRepository.findAll()
    }
/*
  fun save(detail: Detail): Detail {
        val savedDetail = detailRepository.save(detail)
        updateProductStock(savedDetail)
        return savedDetail
    }
    fun updateProductStock(detail: Detail) {
        detail.product_Id?.let { productId ->
            productRepository.findById(productId).ifPresent {
                it.stok = (it.stok ?: 0) - (detail.quantity ?: 0)
                productRepository.save(it)
            }
        }
    }
*/
    fun save(detail: Detail): Detail {
    val response = detailRepository.save(detail)
    val product = productRepository.findById(detail.product_Id)
    product?.let { it ->
        val currentStock = it.stok ?: 0
        it.stok = currentStock - (detail.quantity ?: 0)
        productRepository.save(it)
    }
    return response
}



    fun update(detail: Detail): Detail {
        try {
            detailRepository.findById(detail.id)
                ?: throw Exception("ID no existe")

            return detailRepository.save(detail)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(detail: Detail): Detail{
        try{
            val response = detailRepository.findById(detail.id)
                ?: throw Exception("ID no existe")
            response.apply {
                quantity=detail.quantity //un atributo del modelo
            }
            return detailRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = detailRepository.findById(id)
                ?: throw Exception("ID no existe")
            detailRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?): Detail?{
        return detailRepository.findById(id)
    }


}
