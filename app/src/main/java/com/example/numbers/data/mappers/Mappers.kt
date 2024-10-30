package com.example.numbers.data.mappers

import com.example.numbers.data.database.FactEntity
import com.example.numbers.data.models.Fact
import com.example.numbers.data.network.models.FactResponse

fun FactEntity.mapToFact(): Fact {
    return Fact(
        id = this.id,
        number = this.number,
        text = this.text,
        found = this.found
    )
}

fun FactResponse.mapToEntity(): FactEntity {
    return FactEntity(
        number = this.number,
        text = this.text,
        found = this.found
    )
}