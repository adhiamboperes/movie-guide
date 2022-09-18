package com.adhiambo.movieguide.common.room

interface MovieRoomModel<DomainObject> {

    fun toDomainObject(): DomainObject
}
