package com.example.numbers.data.mappers

import com.example.numbers.data.database.FactEntity
import com.example.numbers.data.models.Fact

fun FactEntity.mapToFact(): Fact {
    return Fact(
        id = this.id,
        number = this.number,
        text = this.fact
    )
}