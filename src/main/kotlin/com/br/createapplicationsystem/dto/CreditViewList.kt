package com.br.createapplicationsystem.dto

import com.br.createapplicationsystem.entity.Credit
import java.math.BigDecimal
import java.util.*

data class CreditViewList(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfIntallments: Int,
) {
    constructor(credit: Credit) : this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfIntallments = credit.numberOfInstallments
    )

}
