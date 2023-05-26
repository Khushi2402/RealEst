package com.example.project.models

data class property(
val _id: Id,
val name: String,
val type: String,
val address: String,
val size: String,
val description: String,
val image: String,
val favorite: Boolean,
val createdAt: CreatedAt,
val updatedAt: UpdatedAt,
val `__v`: Int
) {
    data class Id(
        val `$oid`: String
    )

    data class CreatedAt(
        val `$date`: DateLong
    )

    data class UpdatedAt(
        val `$date`: DateLong
    )

    data class DateLong(
        val `$numberLong`: String
    )
}

