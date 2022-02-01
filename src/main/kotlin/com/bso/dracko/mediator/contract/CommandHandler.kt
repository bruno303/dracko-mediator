package com.bso.dracko.mediator.contract

fun interface CommandHandler<T : Command> {
    fun handle(command: T)
}