package coffeelisp.types

import coffeelisp.assertEquals
import org.junit.Test

class ConsCellTest {
    @Test
    fun testConsCell() {
        "(cons 1 2)".assertEquals("(1 . 2)")
    }

    @Test
    fun testUnits() {
        "(cons () ())".assertEquals("(())")
    }

    @Test
    fun testList() {
        "(cons 1 (cons 1 (cons 2 (cons 3 (cons 5 ())))))".assertEquals("(1 1 2 3 5)")
    }

    @Test
    fun testConsList() {
        "(cons 1 (cons 1 (cons 2 (cons 3 5))))".assertEquals("(1 1 2 3 . 5)")
    }

    @Test
    fun testNested() {
        "(cons (cons 1 2) (cons 3 ()))".assertEquals("((1 . 2) 3)")
    }

    @Test
    fun testCar() {
        "(car (cons 5 3))".assertEquals("5")
        "(car (cons 5 ()))".assertEquals("5")
    }

    @Test
    fun testCdr() {
        "(cdr (cons 5 3))".assertEquals("3")
        "(cdr (cons 5 ()))".assertEquals("()")
    }
}
