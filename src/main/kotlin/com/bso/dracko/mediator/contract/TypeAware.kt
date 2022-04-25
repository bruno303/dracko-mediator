package com.bso.dracko.mediator.contract

interface TypeAware<T> {
    fun getType(): Class<T>
}
