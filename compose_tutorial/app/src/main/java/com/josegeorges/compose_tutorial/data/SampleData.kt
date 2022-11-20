package com.josegeorges.compose_tutorial.data

import com.josegeorges.compose_tutorial.Message

class SampleData {
    companion object {
        val conversationSample = listOf(
            Message(author = "Jose Georges", body = "Paso perro, todo bien?"),
            Message(author = "Eduardo Flores", body = "Si mk tu que?"),
            Message(author = "Jose Georges", body = "Nada, ando ladillado tonces me puse a hacer un tutorial de Android"),
            Message(author = "Eduardo Flores", body = "Calidad mk y que tal?"),
            Message(author = "Jose Georges", body = "Depinga wn, bastante parecido a Flutter la verdad. Lo volvieron declarativo"),
            Message(author = "Eduardo Flores", body = "Sadico wn"),
            Message(author = "Jose Georges", body = "Si wn"),
        )
    }
}