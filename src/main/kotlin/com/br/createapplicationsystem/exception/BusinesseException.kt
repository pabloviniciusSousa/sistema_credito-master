package com.br.createapplicationsystem.exception

data class BusinesseException(override val message: String?): RuntimeException(message) {

}