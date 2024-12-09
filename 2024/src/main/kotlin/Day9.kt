import util.println
import util.swapPositions

fun main() {
    Day9.solvePart1().println()
    Day9.solvePart2().println()
}

object Day9 : Solve<Long, Long>(2024, 9) {
    private val diskMap = input.first().toDiskMap()
    private val fileSystem = diskMap.transformToFileSystem()

    override fun solvePart1(): Long {
        val fileSystemMutable = fileSystem.toMutableList()
        var fileEnd = fileSystemMutable.indexOfLast { it != -1 }
        for (i in fileSystemMutable.indices) {
            if (i >= fileEnd) break
            if (fileSystemMutable[i] == -1) {
                fileSystemMutable.swapPositions(i, fileEnd)
                while (fileSystemMutable[fileEnd] == -1) {
                    fileEnd--
                }
            }
        }
        return fileSystemMutable.checksum()
    }

    override fun solvePart2(): Long {
        val fileSystemMutable = fileSystem.toMutableList()
        var fileEnd = fileSystemMutable.indexOfLast { it != -1 }
        while (fileEnd > 0) {
            val fileStart = fileSystemMutable.subList(0, fileEnd).indexOfLast { it != fileSystemMutable[fileEnd] } + 1
            val fileSize = fileEnd - fileStart + 1
            val freeSpaceIndex = fileSystemMutable.findFreeSpace(fileSize)
            if (freeSpaceIndex != -1 && fileEnd > freeSpaceIndex) {
                for (i in 0 until fileSize) {
                    fileSystemMutable.swapPositions(fileStart + i, freeSpaceIndex + i)
                }
            }
            fileEnd = fileSystemMutable.subList(0, fileStart).indexOfLast { it != -1 }
        }
        return fileSystemMutable.checksum()
    }

    fun List<Int>.transformToFileSystem(): List<Int> {
        val filesystem = MutableList(diskMap.sum()) { -1 }
        var block = 0
        for (i in diskMap.indices) {
            repeat(diskMap[i]) {
                filesystem[block] = if (i % 2 == 0) i / 2 else -1
                block++
            }
        }
        return filesystem
    }

    fun List<Int>.findFreeSpace(needed: Int): Int {
        var start = 0
        var count = 0
        for (i in indices) {
            if (get(i) == -1) {
                if (count++ == 0) start = i
                if (count >= needed) return start
            } else {
                count = 0
            }
        }
        return -1
    }

    fun List<Int>.checksum() = this.mapIndexed { i, it -> if (it == -1) 0 else 1L * i * it }.sum()
    fun String.toDiskMap() = this.map { "$it".toInt() }
}



