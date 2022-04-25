package com.bso.dracko.mediator.contract

interface EventHandler<T : Event> : TypeAware<T> {
    fun handle(event: T)
}
