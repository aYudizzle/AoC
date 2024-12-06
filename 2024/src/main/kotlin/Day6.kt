import util.Direction
import util.Point
import util.Turn
import util.println
import util.toGrid

fun main() {
    Day6.solvePart1().println()
    Day6.solvePart2().println()
}

object Day6: Solve<Int, Int>(2024, 6) {
    override fun solvePart1(): Int {
        val grid = input.toGrid()
        val first = grid.entries.first { it.value == '^' }.key
        val visited = walk(grid, first)!!.map { it.first }.toSet()
        return visited.count()
    }

    override fun solvePart2(): Int {
        val grid = input.toGrid()
        val first = grid.entries.first { it.value == '^' }.key
        val visited = walk(grid, first)!!.map { it.first }.toSet()
        return (visited - first).count { obstacle ->
            walk(grid.toMutableMap().also { it[obstacle] = '#' }, first) == null
        }
    }

    private fun walk(grid: Map<Point, Char>, first: Point): Set<Pair<Point, Direction>>? {
        var current = first
        var direction = Direction.NORTH
        var visited = mutableSetOf(current to direction)

        while(true) {
            val next = current.move(direction)
            if(next !in grid) break
            if(grid[next] == '#') {
                direction = direction.rotate(Turn.RIGHT)
            } else {
                current = next
                if(current to direction in visited) return null
                visited.add(current to direction)
            }
        }
        return visited
    }
}