package coffeelisp

import coffeelisp.syntax.Atom
import coffeelisp.syntax.isIdentifier
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LispObjectTest {
    @Test
    fun testIdentifier() {
        assertTrue((Atom("hello").isIdentifier()))
        assertTrue((Atom("World").isIdentifier()))
        assertTrue((Atom("g").isIdentifier()))
        assertTrue((Atom("g8").isIdentifier()))
    }

    @Test
    fun testCantStartWithNumber() {
        assertFalse((Atom("0").isIdentifier()))
        assertFalse((Atom("2").isIdentifier()))
        assertFalse((Atom("2go").isIdentifier()))
    }

    @Test
    fun testMathSymbolsAreValidIdentifiers() {
        assertTrue(Atom("+").isIdentifier())
        assertTrue(Atom("-").isIdentifier())
        assertTrue(Atom("*").isIdentifier())
        assertTrue(Atom("/").isIdentifier())
    }

    @Test
    fun testEmojisAreIdentifiers() {
        assertTrue(Atom("🥶").isIdentifier())
    }
}
