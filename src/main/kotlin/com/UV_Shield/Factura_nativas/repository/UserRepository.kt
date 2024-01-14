package com.UV_Shield.Factura_nativas.repository

import com.UV_Shield.Factura_nativas.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, String> {
    fun findByUsername(username: String): UserEntity?
}