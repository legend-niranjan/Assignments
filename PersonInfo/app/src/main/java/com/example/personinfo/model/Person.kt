package com.example.personinfo.model

data class Person(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    val image: String?
)