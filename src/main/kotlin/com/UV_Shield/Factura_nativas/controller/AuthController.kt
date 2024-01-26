package com.UV_Shield.Factura_nativas.controller



import com.UV_Shield.Factura_nativas.dto.LoginDto
import com.UV_Shield.Factura_nativas.dto.TokenDto
import com.UV_Shield.Factura_nativas.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.*
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private val authenticationManager: AuthenticationManager? = null
    @Autowired
    private val jwtUtil: JwtUtil? = null

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<*>? {
        try {
            val login = UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)
            val authentication: Authentication = authenticationManager!!.authenticate(login)


            val response = TokenDto().apply { jwt = jwtUtil!!.create(loginDto.username) }

            return ResponseEntity(response, HttpStatus.OK)
        } catch (e: BadCredentialsException) {

            return ResponseEntity("Credenciales incorrectas", HttpStatus.UNAUTHORIZED)
        } catch (e: LockedException) {

            return ResponseEntity("La cuenta está bloqueada", HttpStatus.UNAUTHORIZED)
        } catch (e: DisabledException) {

            return ResponseEntity("La cuenta está deshabilitada", HttpStatus.UNAUTHORIZED)
        } catch (e: AccountExpiredException) {

            return ResponseEntity("La cuenta ha expirado", HttpStatus.UNAUTHORIZED)
        } catch (e: CredentialsExpiredException) {
            return ResponseEntity("Las credenciales han expirado", HttpStatus.UNAUTHORIZED)
        } catch (e: Exception) {

            e.printStackTrace()
            return ResponseEntity("Error de autenticación: ${e.message}", HttpStatus.UNAUTHORIZED)

        }
    }
}
