package coffeelisp.types

import coffeelisp.syntax.Atom
import org.junit.Test
import kotlin.test.assertTrue

class StringTest {

    @Test
    fun testIsString() {
        assertTrue(LispString.isType(Atom("\"Hello\"")))
        assertTrue(LispString.isType(Atom("""
            ""
        """.trimIndent())))
    }

    @Test
    fun testParseString() {
        val lispString = (LispString.toLispObject(
                Atom("""
                    "Hello"
                    """.trimIndent())) as LispString)

        kotlin.test.assertEquals(LispString("Hello"), lispString)
    }
}
