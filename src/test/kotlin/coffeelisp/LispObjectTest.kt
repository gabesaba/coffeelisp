package coffeelisp

import coffeelisp.syntax.Atom
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
        assertFalse((Atom("2go").isIdentifier()))
    }
}
