package com.UV_Shield.Factura_nativas.repository



import com.UV_Shield.Factura_nativas.model.Detail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DetailRepository: JpaRepository<Detail, Long?> {
    fun findById(id: Long?): Detail?
    fun findByInvoiceId(invoiceId: Long): List<Detail>
    @Query(nativeQuery =true)
    fun sumTotal(@Param("invoice_Id") invoice_Id: Long?): Double?

}




