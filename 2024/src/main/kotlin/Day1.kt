import util.println
import kotlin.math.abs

fun main() {
    Day1.solvePart1().println()
    Day1.solvePart2().println()
}

object Day1 : Solve<Int, Int>(2024,1) {
    override fun solvePart1(): Int {
        val a = input.map { it.substringBefore(" ").toInt() }.sorted()
        val b = input.map { it.substringAfterLast(" ").toInt() }.sorted()
        return a.zip(b).sumOf { (x, y) -> abs(x - y) }
    }

    override fun solvePart2(): Int {
        val a = input.map { it.substringBefore(" ").toInt() }
        val b = input.map { it.substringAfterLast(" ").toInt() }
        return a.sumOf { value -> b.count { it == value } * value }
    }
}