package com.br.createapplicationsystem.service

import com.br.createapplicationsystem.entity.Customer

interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long)


}