package cberg.aoc2024

class Day9(private val input: String) {
    constructor(input: Input) : this(input.oneLine())

    fun part1(): Long {
        val list = input.map { it.digitToInt() }.flatMapIndexed { i, n -> List(n) { if (i % 2 == 0) i / 2 else null } }

        var i = 0
        var j = list.lastIndex
        var checksum = 0L
        while (j >= i) {
            if (list[i] != null) {
                checksum += i * list[i]!!
                i++
            } else if (list[j] != null) {
                checksum += i * list[j]!!
                i++
                j--
            } else {
                j--
            }
        }
        return checksum
    }

    fun part2() = parse(input).compact().checksum()

    private fun parse(diskMap: String): List<File> = diskMap.map { it.digitToInt() }
        .mapIndexed { i, n -> File(n, (if (i % 2 == 0) i / 2 else null)) }
        .filterNot { it.size == 0 }

    private data class File(val size: Int, val id: Int?)

    private fun List<File>.compact() = decreasingIds().fold(this) { compactedDisk, id ->
        val filePosition = compactedDisk.findFile(id)
        val file = compactedDisk[filePosition]
        val spacePosition = compactedDisk.findSpace(file.size)

        if (spacePosition in 0 until filePosition) {
            compactedDisk.move(file, from = filePosition, to = spacePosition)
        } else {
            compactedDisk
        }
    }

    private fun List<File>.decreasingIds(): IntProgression = last().id!! downTo 0

    private fun List<File>.findFile(id: Int): Int = this.indexOfLast { it.id == id }

    private fun List<File>.findSpace(size: Int): Int = this.indexOfFirst { it.size >= size && it.id == null }

    private fun List<File>.move(file: File, from: Int, to: Int) = subList(0, to) +
            file +
            File(this[to].size - file.size, null) +
            subList(to + 1, from) +
            File(file.size, null) +
            subList(from + 1, this.size)

    private fun List<File>.checksum(): Long {
        var sum = 0L
        var pos = 0L
        this.forEach { file ->
            if (file.id != null) {
                sum += file.id * (file.size * pos + file.size * (file.size - 1) / 2)
            }
            pos += file.size
        }
        return sum
    }
}
