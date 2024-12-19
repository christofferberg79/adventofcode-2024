package cberg.aoc2024

import cberg.aoc2024.dijkstra.dijkstra

class Day18(private val range: IntRange, input: List<String>) {
    constructor(range: IntRange, input: Input) : this(range, input.lines())

    val bytes = input.map { it.split(",").map { it.toInt() }.let { (x, y) -> Vector(x, y) } }
    val goal = Vector(range.last, range.last)

    fun part1(numBytes: Int): Int {
        val corrupted = bytes.take(numBytes).toSet()
        return findPath(goal, corrupted)?.cost ?: error("No solution found")
    }

    private fun findPath(goal: Vector, corrupted: Set<Vector>) = dijkstra(
        start = Vector(0, 0),
        goal = goal,
        edges = { pos ->
            Dir.nesw.map { dir -> pos + dir }
                .filter { newPos -> newPos !in corrupted && newPos.x in range && newPos.y in range }
                .map { newPos -> Pair(newPos, 1) }
        }
    )

    fun part2(numBytes: Int): String {
        val index = bytes.indices.toList().binarySearch(fromIndex = numBytes) { index ->
            val corrupted = bytes.take(index + 1).toSet()
            findPath(goal, corrupted) == null
        }
        val byte = bytes[index]
        return "${byte.x},${byte.y}"
    }
}
