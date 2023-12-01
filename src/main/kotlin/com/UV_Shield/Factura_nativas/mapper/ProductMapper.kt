package com.UV_Shield.Factura_nativas.mapper

import com.UV_Shield.Factura_nativas.dto.ProductDto
import com.UV_Shield.Factura_nativas.model.Product
import com.UV_Shield.Factura_nativas.repository.ProductRepository


class ProductMapper(private val productRepository: ProductRepository) {

    companion object {
        fun mapToDto(product: Product): ProductDto {
            return ProductDto(
                product.id,
                "${product.description} ${product.brand}"
            )
        }
    }

    fun listDto(): List<ProductDto> {
        val productList = productRepository.findAll()
        val productDtoList = mutableListOf<ProductDto>()

        productList.map { product ->
            val productDto = mapToDto(product)
            productDtoList.add(productDto)
        }

        return productDtoList
    }
}

