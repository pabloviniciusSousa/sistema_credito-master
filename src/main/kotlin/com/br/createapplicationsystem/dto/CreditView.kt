package com.br.createapplicationsystem.dto

import com.br.createapplicationsystem.entity.Credit
import com.br.createapplicationsystem.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class CreditView(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfIntallments: Int,
    val status: Status,
    val emailCustomer: String?,
    val  incomeCustomer: BigDecimal?
) {

    constructor(credit: Credit): this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfIntallments = credit.numberOfInstallments,
        status = credit.status,
        emailCustomer = credit.customer?.email,
        incomeCustomer = credit.customer?.income
    )

}
