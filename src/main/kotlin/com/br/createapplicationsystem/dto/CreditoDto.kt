package com.br.createapplicationsystem.dto

import com.br.createapplicationsystem.entity.Credit
import com.br.createapplicationsystem.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditoDto(
    @field:NotNull val creditValue: BigDecimal,
    @field:Future val dayFirstOfInstallment: LocalDate,
    val numberOfIntallments: Int,
    @field:NotNull val customerId: Long
) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfIntallments,
        customer = Customer(id = this.customerId)
    )

}
