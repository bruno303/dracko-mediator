package com.bso.dracko.mediator.contract

interface Mediator {
    fun <T : Command> dispatch(command: T)
    fun <T : Request<R>, R> dispatch(request: T): R?
    fun <T : Event> dispatch(event : T)
}