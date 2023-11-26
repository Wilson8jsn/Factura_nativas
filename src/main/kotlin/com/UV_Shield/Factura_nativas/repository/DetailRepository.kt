package com.UV_Shield.Factura_nativas.repository



import com.UV_Shield.Factura_nativas.model.Detail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DetailRepository: JpaRepository<Detail, Long?> {
    fun findById (id: Long?): Detail?

}
