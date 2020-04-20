package coffeelisp.functions

import coffeelisp.assertEquals
import coffeelisp.assertFails
import org.junit.Test

class MathTest {

    @Test
    fun testIdentityWhenNoArgs() {
        "(+)".assertEquals("0")
        "(*)".assertEquals("1")
        "(=)".assertEquals("#t")
    }

    @Test
    fun testSingleArgIdentity() {
        "(* 0)".assertEquals("0")
        "(* 1)".assertEquals("1")
        "(* -1)".assertEquals("-1")

        "(+ 0)".assertEquals("0")
        "(+ 1)".assertEquals("1")
        "(+ -1)".assertEquals("-1")
    }

    @Test
    fun testPlusIdentity() {
        "(+ 0 0)".assertEquals("0")

        "(+ 1 0)".assertEquals("1")
        "(+ 0 1)".assertEquals("1")

        "(+ -1 0)".assertEquals("-1")
        "(+ 0 -1)".assertEquals("-1")
    }

    @Test
    fun testPlusWithNegativeNumbers() {
        "(+ -0 0)".assertEquals("0")
        "(+ 0 -0)".assertEquals("0")
        "(+ -0 -0)".assertEquals("0")

        "(+ 0 -1)".assertEquals("-1")
        "(+ -1 0)".assertEquals("-1")

        "(+ 1 -1)".assertEquals("0")
        "(+ -1 1)".assertEquals("0")

        "(+ 5 -3)".assertEquals("2")
        "(+ 3 -5)".assertEquals("-2")
    }

    @Test
    fun testSubRequiresAtLeastOneArg() {
        "(-)".assertFails()
    }

    @Test
    fun testNegative() {
        "(- 0)".assertEquals("0")
        "(- 1)".assertEquals("-1")
        "(- 5)".assertEquals("-5")
    }

    @Test
    fun testSubtraction() {
        "(- 0 0)".assertEquals("0")
        "(- 1 1)".assertEquals("0")
        "(- 1 0)".assertEquals("1")
        "(- 3 0)".assertEquals("3")
        "(- 3 2)".assertEquals("1")
        "(- 3 5)".assertEquals("-2")


        "(- 0)".assertEquals("0")
        "(- 0 1)".assertEquals("-1")
        "(- 0 1 2)".assertEquals("-3")
        "(- 0 1 2 3)".assertEquals("-6")
        "(- 0 1 2 3 4)".assertEquals("-10")

        "(- 10 1)".assertEquals("9")
        "(- 10 1 2)".assertEquals("7")
        "(- 10 1 2 3)".assertEquals("4")
        "(- 10 1 2 3 4)".assertEquals("0")
    }

    @Test
    fun testEqualityWithOneArg() {
        "(= -1)".assertEquals("#t")
        "(= 0)".assertEquals("#t")
        "(= 1)".assertEquals("#t")
    }

    @Test
    fun testEqualityWithTwoArgs() {
        "(= -1 -1)".assertEquals("#t")
        "(= 0 0)".assertEquals("#t")
        "(= 1 1)".assertEquals("#t")
    }

    @Test
    fun testInequality() {
        "(= 0 1)".assertEquals("#f")
        "(= 1 0)".assertEquals("#f")

        "(= 0 -1)".assertEquals("#f")
        "(= -1 0)".assertEquals("#f")

        "(= 1 -1)".assertEquals("#f")
        "(= -1 1)".assertEquals("#f")
    }

    @Test
    fun testFailureWhenNonNumber() {
        "(= ())".assertFails()
        "(= #f)".assertFails()
        "(= #t)".assertFails()
        "(= 1 ())".assertFails()
    }

    @Test
    fun testShortCircuit() {
        "(= 0 1 ())".assertEquals("#f")
        "(= 0 1 #f)".assertEquals("#f")
        "(= 0 1 #t)".assertEquals("#f")
        "(= 0 1 ())".assertEquals("#f")
    }
}
