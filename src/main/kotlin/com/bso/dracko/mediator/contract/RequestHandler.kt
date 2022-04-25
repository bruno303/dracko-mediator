package com.bso.dracko.mediator.contract

interface RequestHandler<T : Request<R>, R> : TypeAware<T> {
    fun handle(request: T): R
}
