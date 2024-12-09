import util.println
import util.toAllLongs

fun main() {
    Day7.solvePart1().println()
    Day7.solvePart2().println()
}

object Day7 : Solve<Long, Long>(2024, 7) {
    override fun solvePart1(): Long {
        val equations = input.map(Equation::parse)
        return equations.filter {
            it.isFeasible { a, b ->
                listOf(
                    a + b,
                    a * b,
                )
            }
        }.sumOf {
            it.result
        }
    }

    fun evaluate1(result: Long, remaining: List<Long>): List<Long> {
        if (remaining.isEmpty()) return listOf(result)
        return evaluate1(result + remaining.first(), remaining.drop(1)) +
                evaluate1(result * remaining.first(), remaining.drop(1))
    }

    override fun solvePart2(): Long {
        val equations = input.map(Equation::parse)
        return equations.filter {
            it.isFeasible { a, b ->
                listOf(
                    a + b,
                    a * b,
                    "$a$b".toLong()
                )
            }
        }.sumOf {
            it.result
        }
    }

    data class Equation(val result: Long, val numbers: List<Long>) {
        companion object {
            fun parse(input: String): Equation = input.toAllLongs().toList().run {
                Equation(this[0], this.drop(1))
            }
        }
    }

    fun Equation.isFeasible(allOperatorsApplied: (Long, Long) -> List<Long>): Boolean {
        if (numbers.size == 2) return allOperatorsApplied(numbers[0], numbers[1]).any { it == result }
        // there are no negative numbers
        if (numbers[0] > result) return false
        return allOperatorsApplied(numbers[0], numbers[1]).map { Equation(result, listOf(it) + numbers.drop(2)) }
            .any { it.isFeasible(allOperatorsApplied) }
    }
}