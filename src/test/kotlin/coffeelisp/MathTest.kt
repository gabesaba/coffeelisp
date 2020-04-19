package coffeelisp

import coffeelisp.types.LispNumber
import coffeelisp.env.interpret
import org.junit.Test
import kotlin.test.assertEquals

class MathTest {
    @Test
    fun testSubtraction() {
        assertEquals(LispNumber(0.toBigInteger()), interpret("(sub1 1)"))
    }

    @Test fun testAddition() {
        assertEquals(LispNumber(1.toBigInteger()), interpret("(add1 0)"))
    }
}
