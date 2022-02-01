package com.bso.dracko.mediator

import com.bso.dracko.mediator.contract.*

class MediatorImpl(private val registry: Registry) : Mediator {

    override fun <T : Command> dispatch(command: T) {
        val handler = registry.getCommandHandler(command)
        handler?.handle(command)
    }

    override fun <T : Request<R>, R> dispatch(request: T): R? {
        val handler = registry.getRequestHandler(request)
        return handler?.handle(request)
    }

    override fun <T : Event> dispatch(event: T) {
        val handlers = registry.getEventHandler(event)
        handlers?.forEach {
            h : EventHandler<T> ->
            h.handle(event)
        }
    }
}