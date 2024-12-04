import util.Direction8
import util.Direction8.*
import util.println
import util.toGrid

fun main() {
    Day4.solvePart1().println()
    Day4.solvePart2().println()
}

object Day4: Solve<Int, Int>(2024,4) {
    override fun solvePart1(): Int {
        return input.toGrid().let { grid ->
            grid.keys.sumOf { point ->
                Direction8.entries.count {
                    List(4) { distance -> grid[point.move(it,distance)] }.joinToString("") == "XMAS"
                }
            }
        }
    }

    override fun solvePart2(): Int {
        return input.toGrid().let { grid ->
            grid.keys.count { point ->
                val diag1 = List(3) { grid[point.move(NORTH_WEST).move(SOUTH_EAST,it)] }.joinToString("")
                val diag2 = List(3) { grid[point.move(SOUTH_WEST).move(NORTH_EAST, it)] }.joinToString("")
                listOf(diag1, diag2).all { it == "MAS" || it.reversed() == "MAS" }
            }
        }
    }
}
