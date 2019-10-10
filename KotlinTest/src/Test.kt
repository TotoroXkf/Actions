class Point(val x: Int, val y: Int) {
    operator fun unaryPlus(): Point {
        return Point(-x, -y)
    }
}

fun main() {
}