package com.bso.dracko.mediator.contract

interface Registry {
    fun <Q : Request<R>, R> getRequestHandler(request: Q): RequestHandler<Q, R>?
    fun <T : Command> getCommandHandler(command: T): CommandHandler<T>?
    fun <E : Event> getEventHandler(event: E) : List<EventHandler<E>>?
}