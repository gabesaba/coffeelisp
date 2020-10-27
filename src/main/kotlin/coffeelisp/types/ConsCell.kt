package coffeelisp.types

class ConsCell(val car: LispObject, val cdr: LispObject): LispObject {

    override val typeName = "Cons Cell"

    override fun display(): String {
        return display(true)
    }

    private fun display(withParens: Boolean): String {
        val cdrStr = when (cdr) {
            is ConsCell -> " ${cdr.display(false)}"
            is LispUnit -> ""
            else -> " . ${cdr.display()}"
        }
        val inner = "${car.display()}$cdrStr"
        return if (withParens) {
            "($inner)"
        } else {
            inner
        }
    }
}
