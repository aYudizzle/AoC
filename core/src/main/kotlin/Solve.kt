import kotlin.io.path.Path
import kotlin.io.path.readText

abstract class Solve<P1, P2> (
    private val year: Int,
    private val day: Int,
){
    val input: List<String> = Path("$year/src/input/day$day.txt").readText().trim().lines()
    abstract fun solvePart1(): P1
    abstract fun solvePart2(): P2
}

