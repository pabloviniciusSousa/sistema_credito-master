package com.br.createapplicationsystem.service.impl

import com.br.createapplicationsystem.entity.Customer
import com.br.createapplicationsystem.exception.BusinesseException
import com.br.createapplicationsystem.repository.CustomerRepository
import com.br.createapplicationsystem.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow{
        throw BusinesseException("Id $id not found")
    }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }



}