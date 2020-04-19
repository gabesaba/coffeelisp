package coffeelisp.types

import coffeelisp.assertEquals
import org.junit.Test

class UnitTest {
    @Test
    fun testType() {
        "(type? ())".assertEquals("Unit")
    }

    @Test
    fun testIsUnit() {
        "(unit? ())".assertEquals("#t")
    }

    @Test
    fun testNumberIsNotUnit() {
        "(unit? 5)".assertEquals("#f")
    }

    @Test
    fun testCdrOfListIsUnit() {
        "(unit? (cdr (cons 1 ())))".assertEquals("#t")
    }
}
