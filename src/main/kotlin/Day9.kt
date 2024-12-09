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

    fun part2(): Long {
        var list = input.map { it.digitToInt() }
            .mapIndexed { i, n -> File(n, (if (i % 2 == 0) i / 2 else null)) }
            .filterNot { it.size == 0 }

        for (id in (list.last().id!! downTo 0)) {
            val index = list.indexOfLast { it.id == id }
            val file = list[index]

            val foundIndex = list.indexOfFirst { it.size >= file.size && it.id == null }
            if (foundIndex in 0 until index) {
                val foundSpace = list[foundIndex]
                list = buildList {
                    addAll(list.subList(0, foundIndex))
                    add(file)
                    if (foundSpace.size > file.size) add(File(foundSpace.size - file.size, null))
                    addAll(list.subList(foundIndex + 1, index))
                    add(File(file.size, null))
                    addAll(list.subList(index + 1, list.size))
                }
            }
        }

        var sum = 0L
        var pos = 0L
        list.forEach { file ->
            if (file.id != null) {
                sum += file.id * (file.size * pos + file.size * (file.size - 1) / 2)
            }
            pos += file.size
        }

        return sum
    }

    private data class File(val size: Int, val id: Int?)
}
