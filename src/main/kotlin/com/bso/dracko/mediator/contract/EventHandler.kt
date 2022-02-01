package com.bso.dracko.mediator.contract

fun interface EventHandler<T : Event> {
    fun handle(event: Event)
}