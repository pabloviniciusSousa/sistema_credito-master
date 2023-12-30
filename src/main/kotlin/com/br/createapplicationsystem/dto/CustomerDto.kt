package com.br.createapplicationsystem.dto

import com.br.createapplicationsystem.entity.Address
import com.br.createapplicationsystem.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto (
    @field:NotEmpty(message = "Precisa digitar um nome") val firstName: String,
    @field:NotEmpty(message = "Precisa digitar o ultimo nome") val lastName: String,
    @field:NotEmpty(message = "CPF vazio")
    @CPF(message = "Este CPF é invalido")val cpf: String,
    @field:NotNull(message = "Invalido dados") val income: BigDecimal,
    @field:NotEmpty(message = "Precisa digitar um email")
    @field:Email(message = "Email invalido") val email: String,
    @field:NotEmpty(message = "Precisa digitar uma senha") val password: String,
    @field:NotEmpty(message = "Precisa digitar um cep") val zipcode: String,
    @field:NotEmpty(message = "Precisa digitar uma rua ") val street: String
){

    fun toEntity():Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipcode = this.zipcode,
            street = this.street
        )
    )
}