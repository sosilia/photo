package com.example.photo.data

import com.example.photo.domain.model.Model

interface EntityMapper<M : Model, E : Entity> {
    fun mapToDomain(entity: E): M

    fun mapToEntity(model: M): E
}