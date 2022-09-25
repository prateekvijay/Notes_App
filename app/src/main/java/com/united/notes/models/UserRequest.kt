package com.united.notes.models

data class UserRequest(
    val email: String,
    val password: String,
    val username: String
)