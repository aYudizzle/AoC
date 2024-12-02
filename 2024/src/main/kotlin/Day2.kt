import util.println
import kotlin.math.abs

fun main() {
    Day2.solvePart1().println()
    Day2.solvePart2().println()
}

object Day2 : Solve<Int, Int>(2024, 2) {
    override fun solvePart1(): Int {
        return input.map { it.split(" ").map(String::toInt) }.count {
            val sortedList = it.sorted()
            (it == sortedList || it == sortedList.reversed()) && it.windowed(2).all { (x, y) -> abs(x - y) in 1..3 }
        }
    }

    override fun solvePart2(): Int {
        return input.map { it.split(" ").map(String::toInt) }.count {
            (0..it.size).any { index ->
                val new = it.take(index) + it.drop(index + 1)
                val sortedList = new.sorted()
                (new == sortedList || new == sortedList.reversed()) && new.windowed(2)
                    .all { (x, y) -> abs(x - y) in 1..3 }
            }
        }
    }
}