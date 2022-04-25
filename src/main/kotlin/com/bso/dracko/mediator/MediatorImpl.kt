package com.bso.dracko.mediator

import com.bso.dracko.mediator.contract.*

class MediatorImpl(private val registry: Registry) : Mediator {

    override fun <T : Command> dispatch(command: T) {
        registry.getCommandHandler(command)?.handle(command)
    }

    override fun <T : Request<R>, R> dispatch(request: T): R? {
        return registry.getRequestHandler(request)?.handle(request)
    }

    override fun <T : Event> dispatch(event: T) {
        registry.getEventHandler(event).forEach { it.handle(event) }
    }
}
