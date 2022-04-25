package com.bso.dracko.mediator

import com.bso.dracko.mediator.contract.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any

internal class MediatorImplTest {

    class DummyCommand : Command
    class DummyRequest : Request<Int>
    class DummyEvent : Event

    private lateinit var mediatorImpl: MediatorImpl
    private val registry: Registry = Mockito.mock(Registry::class.java)

    @BeforeEach
    fun setup() {
        mediatorImpl = MediatorImpl(registry)
    }

    @Test
    fun testDispatchCommandHandler() {
        var called = false

        val handle = object : CommandHandler<DummyCommand> {
            override fun handle(command: DummyCommand) {
                println(command)
                called = true
            }
            override fun getType(): Class<DummyCommand> = DummyCommand::class.java
        }

        `when`(registry.getCommandHandler(any(DummyCommand::class.java))).thenReturn(handle)

        mediatorImpl.dispatch(DummyCommand())

        assertTrue(called)
    }

    @Test
    fun testDispatchRequestHandler() {
        var called = false

        val handle = object : RequestHandler<DummyRequest, Int> {
            override fun handle(request: DummyRequest): Int {
                println(request)
                called = true
                return 1
            }

            override fun getType(): Class<DummyRequest> = DummyRequest::class.java
        }

        `when`(registry.getRequestHandler(any(DummyRequest::class.java))).thenReturn(handle)

        val result = mediatorImpl.dispatch(DummyRequest())
        assertEquals(1, result)
        assertTrue(called)
    }

    @Test
    fun testDispatchEventHandler() {
        var info = 0

        val handle1 = object : EventHandler<DummyEvent> {
            override fun handle(event: DummyEvent) {
                info += 1
            }

            override fun getType(): Class<DummyEvent> = DummyEvent::class.java
        }

        val handle2 = object : EventHandler<DummyEvent> {
            override fun handle(event: DummyEvent) {
                info += 1
            }

            override fun getType(): Class<DummyEvent> = DummyEvent::class.java
        }

        `when`(registry.getEventHandler(any(DummyEvent::class.java))).thenReturn(listOf(handle1, handle2))

        mediatorImpl.dispatch(DummyEvent())

        assertEquals(2, info)
    }
}
