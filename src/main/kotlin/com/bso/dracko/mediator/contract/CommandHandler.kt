package com.bso.dracko.mediator.contract

interface CommandHandler<T : Command> : TypeAware<T> {
    fun handle(command: T)
}
