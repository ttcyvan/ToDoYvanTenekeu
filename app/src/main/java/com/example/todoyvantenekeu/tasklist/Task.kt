package com.example.todoyvantenekeu.tasklist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class Task(
        @SerialName("id")
        val id: String,
        @SerialName("title")
        val title: String,
        @SerialName("description")
        val description: String,
): java.io.Serializable