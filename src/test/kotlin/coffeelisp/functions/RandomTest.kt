package coffeelisp.functions

import coffeelisp.env.eval
import coffeelisp.syntax.LispError
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFailsWith

class RandomTest {
    @Test
    fun testOverBound() {
        val maxInt = Int.MAX_VALUE
        assertFailsWith(LispError::class) {
            "(random (+ $maxInt 1))".eval()
        }
    }

    @Test
    fun testMaxBound() {
        val maxInt = Int.MAX_VALUE
        "(random $maxInt)".eval()
    }

    @Test
    fun testUnderBound() {
        assertFails {
            "(random 0)".eval()
        }
    }

    @Test
    fun testMinBound() {
        assertEquals("0", "(random 1)".eval())
    }
}
