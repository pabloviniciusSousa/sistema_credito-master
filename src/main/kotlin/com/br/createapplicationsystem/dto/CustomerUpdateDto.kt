package com.br.createapplicationsystem.dto

import com.br.createapplicationsystem.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Precisa digitar um nome")  val firstName: String,
    @field:NotEmpty(message = "Precisa digitar um nome")  val lastName: String,
    @field:NotNull(message = "Invalido dados") val income: BigDecimal,
    @field:NotEmpty(message = "Precisa digitar um nome")  val zipcode: String,
    @field:NotEmpty(message = "Precisa digitar um nome")  val street: String
) {

    fun toEntity(customer: Customer): Customer{
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipcode = this.zipcode
        return customer
    }

}
