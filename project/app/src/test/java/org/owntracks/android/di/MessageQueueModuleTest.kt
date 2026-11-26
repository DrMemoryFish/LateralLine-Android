package org.owntracks.android.di

import org.junit.Assert.assertEquals
import org.junit.Test

class MessageQueueModuleTest {
  @Test
  fun `travel build queue capacity is five million`() {
    assertEquals(5_000_000, MessageQueueModule.QUEUE_CAPACITY)
  }
}
