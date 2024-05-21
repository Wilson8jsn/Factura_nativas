package com.UV_Shield.Factura_nativas.service




import com.UV_Shield.Factura_nativas.model.Product
import com.UV_Shield.Factura_nativas.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list(): List<Product> {
        return productRepository.findAll()
    }

    fun save(product: Product): Product {
        try {
            product.stok?.takeIf { it >= 0 }
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock no debe ser menor a cero")

            return productRepository.save(product)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la solicitud", ex)
        }
    }


    fun update(product: Product): Product {
        try {
            productRepository.findById(product.id)
                ?: throw Exception("ID no existe")

            return productRepository.save(product)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(product: Product): Product {
        try {
            val response = productRepository.findById(product.id)
                ?: throw Exception("ID no existe")
            response.apply {
                description = product.description
            }
            return productRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long?): Boolean? {
        try {
            val response = productRepository.findById(id)

                ?: throw Exception("ID no existe")
            productRepository.deleteById(id!!)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun listById(id: Long?): Product? {
        return productRepository.findById(id)
    }

    fun list(pageable: Pageable, product: Product): Page<Product> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher("field", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return productRepository.findAll(Example.of(product, matcher), pageable)
    }

    /* fun listDto() : List<ProductDto> {
        val productList = productRepository.findAll()
    val  productDto = ProductMapper.mapToDto(product)
    } */





}