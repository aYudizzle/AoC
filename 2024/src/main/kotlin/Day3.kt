import util.println

fun main() {
    Day3.solvePart1().println()
    Day3.solvePart2().println()
}

object Day3: Solve<Int, Int>(2024,3) {
    override fun solvePart1(): Int {
        return input.flatMap { extractPairs(it) }.sumOf { (a,b) ->
            a * b
        }
    }

    override fun solvePart2(): Int {
        val regex = Regex("""(mul\((\d+),(\d+)\)|do\(\)|don't\(\))""")
        var enabled = true

        return input.map {
            regex.findAll(it).sumOf {
                if ("mul" in it.value) {
                    if (enabled) return@sumOf it.groupValues[2].toInt() * it.groupValues[3].toInt()
                } else if ("'" in it.value) enabled = false
                else enabled = true
                0
            }
        }.sum()
    }

    private fun extractPairs(line: String): List<Pair<Int, Int>> {
        val regex = Regex("""mul\((\d+),(\d+)\)""")
        val matches = regex.findAll(line)
        return matches.map { match ->
            val (first, second) = match.destructured
            Pair(first.toInt(), second.toInt())
        }.toList()
    }
}