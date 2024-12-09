import util.println

fun main() {
    Day8.solvePart1().println()
    Day8.solvePart2().println()
}

object Day8 : Solve<Int, Int>(2024, 8) {
    private val boundary = Rect(0..input[0].lastIndex, 0..input.lastIndex)
    private val frequencies = hashMapOf<Char, MutableList<Index>>()

    init {
        for (y in input.indices) {
            val row = input[y]
            for (x in row.indices) if (row[x] != '.') frequencies.getOrPut(row[x]) { mutableListOf() }.add(Index(x, y))
        }
    }

    override fun solvePart1(): Int {
        return solve(frequencies) { a, b ->
            val diff = a - b
            for (antinode in arrayOf(a + diff, b - diff)) if (antinode in boundary) add(antinode)
        }
    }

    override fun solvePart2(): Int {
        return solve(frequencies) { a, b ->
            add(a)
            add(b)
            val diff = a - b
            val antinodes = arrayOf(a + diff, b - diff)
            while (antinodes[0] in boundary) {
                add(antinodes[0]); antinodes[0] += diff
            }
            while (antinodes[1] in boundary) {
                add(antinodes[1]); antinodes[1] -= diff
            }
        }
    }

    private inline fun solve(
        frequencies: Map<Char, List<Index>>,
        find: MutableSet<Index>.(a: Index, b: Index) -> Unit
    ): Int {
        val antinodes = hashSetOf<Index>()
        for ((_, v) in frequencies) {
            if (v.size < 2) continue
            for (i in 0..<v.lastIndex) for (j in i + 1..v.lastIndex) antinodes.find(v[i], v[j])
        }
        return antinodes.size
    }


    data class Index(val x: Int, val y: Int)

    operator fun Index.plus(other: Index) = Index(x + other.x, y + other.y)
    operator fun Index.minus(other: Index) = Index(x - other.x, y - other.y)

    data class Rect(val x: IntRange, val y: IntRange)

    operator fun Rect.contains(other: Index) = other.y in y && other.x in x

}