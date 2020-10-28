package coffeelisp.types

import coffeelisp.assertEquals
import coffeelisp.syntax.Atom
import org.junit.Test
import kotlin.test.assertTrue

class StringTest {
    @Test
    fun testTypeString() {
        """
            ""
        """.assertString()

        """
            "hello"
        """.assertString()

        """
            "hello world"
        """.assertString()
    }

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

    private fun String.assertString() {
        "(type? $this)".assertEquals("String")
    }
}
