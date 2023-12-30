package com.br.createapplicationsystem.controller

import com.br.createapplicationsystem.dto.CreditView
import com.br.createapplicationsystem.dto.CreditViewList
import com.br.createapplicationsystem.dto.CreditoDto
import com.br.createapplicationsystem.entity.Credit
import com.br.createapplicationsystem.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors


@RestController
@RequestMapping("/credit")
class CreditController(
    private val creditService: CreditService
){

    @PostMapping
    fun savedCredit(@RequestBody @Valid creditDto: CreditoDto): ResponseEntity<String>{
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long) : ResponseEntity<List<CreditViewList>>{
        val creditViewList: List<CreditViewList> = this.creditService.findAllByCustomer(customerId).stream()
            .map { credit: Credit -> CreditViewList(credit) }.collect(Collectors.toList())

        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @PathVariable creditCode: UUID
                        ) : ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))

    }
}

