import util.println
import util.swapPositions

fun main() {
    Day5.solvePart1().println()
    Day5.solvePart2().println()
}

object Day5 : Solve<Int, Int>(2024, 5) {
    private val rules = input.mapNotNull { it.takeIf { line -> line.contains('|') } }.map { line -> line.split('|').map { it.toInt() } }
    private val updates = input.mapNotNull { it.takeIf { line -> line.contains(',') } }.map { line -> line.split(',').map { it.toInt() } }

    override fun solvePart1(): Int {
        return updates.filter { update ->
            rules.filter { it.all { it in update } }.all { (a, b) -> update.indexOf(a) < update.indexOf(b) }
        }.sumOf { it[it.size / 2] }
    }

    override fun solvePart2(): Int {
        return updates.filterNot { update -> rules.filter { it.all { it in update } }.all { (a, b) -> update.indexOf(a) < update.indexOf(b) } }
            .sumOf { update ->
                update.toMutableList().let { list ->
                    for(i in 0..(list.size / 2)) {
                        for(j in i..list.lastIndex) {
                            if(rules.any { (first, second) -> first == list[j] && second == list[i] }) {
                                list.swapPositions(i,j)
                            }
                        }
                    }
                    list[list.size/2]
                }
            }
    }
}