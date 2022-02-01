package com.bso.dracko.mediator.contract

fun interface RequestHandler<T : Request<R>?, R> {
    fun handle(request: T): R
}