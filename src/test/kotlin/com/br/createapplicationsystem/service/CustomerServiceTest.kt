package com.br.createapplicationsystem.service

import com.br.createapplicationsystem.entity.Address
import com.br.createapplicationsystem.entity.Customer
import com.br.createapplicationsystem.exception.BusinesseException
import com.br.createapplicationsystem.repository.CustomerRepository
import com.br.createapplicationsystem.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*


@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `should create customer`(){
        //given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer
        //when
        val actual: Customer = customerService.save(fakeCustomer)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun `should find customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        //when
        val actual: Customer = customerService.findById(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinesseException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should delete customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs
        //when
        customerService.delete(fakeId)
        //then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }




    private fun buildCustomer(
        firstName: String = "pablo",
        lastName: String = "Vini",
        cpf: String = "28475934625",
        email: String = "vinipb@gmail.com",
        password: String = "123",
        zipCode: String = "123",
        street: String = "Rua da Victor",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipcode = zipCode,
            street = street,
        ),
        income = income,
        id = id
    )
}