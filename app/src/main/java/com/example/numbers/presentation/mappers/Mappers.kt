package com.example.numbers.presentation.mappers

import com.example.numbers.data.models.Fact
import com.example.numbers.presentation.ui.models.FactUi

fun Fact.mapToFactUi(): FactUi {
    return FactUi(
        id = this.id,
        number = this.number,
        fact = this.text
    )
}