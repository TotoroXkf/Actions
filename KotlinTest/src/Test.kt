import java.time.LocalDate
import kotlin.reflect.KProperty

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> = object : Iterator<LocalDate> {
    var current = start

    override fun hasNext(): Boolean = current < endInclusive

    override fun next(): LocalDate = current.apply {
        current = plusDays(1)
    }
}

class Point(val x: Int, val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}

class Foo {
    var p by Delegate()
}

class Delegate() {
    var value = 0

    operator fun getValue(foo: Foo, property: KProperty<*>): Int {
        return value
    }

    operator fun setValue(foo: Foo, property: KProperty<*>, newValue: Int) {
        value = newValue
    }
}


fun main() {
    val foo = Foo()
    println(foo.p)
    foo.p = 12
    println(foo.p)
}