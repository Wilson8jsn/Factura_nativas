package com.UV_Shield.Factura_nativas.model

import jakarta.persistence.*


@Entity
@Table(name = "detail")
class Detail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var quantity: Int? = null
    var price: Int? = null
    @Column(name="invoice_Id")
    var invoice_Id: Long? = null
    @Column(name="product_id")
    var product_Id: Long? = null

}