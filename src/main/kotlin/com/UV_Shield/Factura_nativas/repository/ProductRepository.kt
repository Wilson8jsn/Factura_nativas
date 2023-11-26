package com.UV_Shield.Factura_nativas.repository



import com.UV_Shield.Factura_nativas.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long?> {
    fun findById (id: Long?): Product?

}