package coffeelisp.functions

import coffeelisp.env.eval
import org.junit.Test
import kotlin.test.assertEquals

class TypePredicatesTest {
    @Test
    fun testBooleanTrueLiteral() {
        assertEquals("#t", "(boolean? #t)".eval())
    }

    @Test
    fun testBooleanFalseLiteral() {
        assertEquals("#t", "(boolean? #f)".eval())
    }

    @Test
    fun testEmptyListIsNotConsCell() {
        assertEquals("#f", "(cons? (list))".eval())
    }

    @Test
    fun testSingletonListIsConsCell() {
        assertEquals("#t", "(cons? (list 1))".eval())
    }

    @Test
    fun testNumberIsNotBoolean() {
        assertEquals("#f", "(boolean? 0)".eval())
    }

    @Test
    fun testBuiltInIsFn() {
        assertEquals("#t", "(fn? fn?)".eval())
    }

    @Test
    fun testBooleanIsNotFn() {
        assertEquals("#f", "(fn? #f)".eval())
    }

    @Test
    fun testZeroIsNumber() {
        assertEquals("#t", "(number? 0)".eval())
    }

    @Test
    fun testOneIsNumber() {
        assertEquals("#t", "(number? 1)".eval())
    }

    @Test
    fun testNegOneIsNumber() {
        assertEquals("#t", "(number? -1)".eval())
    }

    @Test
    fun testBooleanIsNotNumber() {
        // take that C++!
        assertEquals("#f", "(number? #t)".eval())
    }

    @Test
    fun testEmptyStringIsString() {
        assertEquals("#t", "(string? \"\")".eval())
    }

    @Test
    fun testGabeIsString() {
        assertEquals("#t", "(string? \"Gabe\")".eval())
    }

    @Test
    fun testNumberIsNotString() {
        assertEquals("#f", "(string? 314159265358)".eval())
    }

    @Test
    fun testEmptyListIsUnit() {
        assertEquals("#t", "(unit? (list))".eval())
    }

    @Test
    fun testCdrOfSingletonListIsUnit() {
        assertEquals("#t", "(unit? (cdr (list 0)))".eval())
    }

    @Test
    fun testCarOfSingletonListIsNotUnit() {
        assertEquals("#f", "(unit? (car (list 0)))".eval())
    }

    @Test
    fun testUnitLiteralIsUnit() {
        assertEquals("#t", "(unit? ())".eval())
    }

    @Test
    fun testNumberIsNotUnit() {
        assertEquals("#f", "(unit? -25)".eval())
    }
}
