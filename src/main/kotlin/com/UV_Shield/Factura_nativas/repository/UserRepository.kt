package com.UV_Shield.Factura_nativas.repository



import com.UV_Shield.Factura_nativas.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
    fun findByUsername(username: String): User?
}