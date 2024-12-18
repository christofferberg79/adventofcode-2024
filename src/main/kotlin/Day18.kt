package cberg.aoc2024

import kotlin.math.min

class Day18(private val range: IntRange, input: List<String>) {
    constructor(range: IntRange, input: Input) : this(range, input.lines())

    val bytes = input.map { it.split(",").map { it.toInt() }.let { (x, y) -> Vector(x, y) } }

    fun part1(numBytes: Int): Int {
        val corrupted = bytes.take(numBytes).toSet()
        return costOfShortestPath(corrupted)
    }

    private fun costOfShortestPath(corrupted: Set<Vector>): Int {
        val todo = mutableMapOf(Vector(0, 0) to 0)
        val done = mutableSetOf<Vector>()
        while (todo.isNotEmpty()) {
            val (pos, cost) = todo.minBy { (_, cost) -> cost }
            todo -= pos
            done += pos
            if (pos == Vector(range.last, range.last)) {
                return cost
            }
            val newCost = cost + 1
            Dir.nesw.map { dir -> pos + dir }
                .filter { newPos -> newPos !in corrupted && newPos !in done && newPos.x in range && newPos.y in range }
                .forEach { newPos ->
                    todo.merge(newPos, newCost) { a, b -> min(a, b) }
                }
        }
        return -1
    }

    fun part2(numBytes: Int): String {
        val corrupted = bytes.take(numBytes).toMutableSet()
        val byte = bytes.drop(numBytes).first { byte ->
            corrupted.add(byte) && costOfShortestPath(corrupted) == -1
        }
        return "${byte.x},${byte.y}"
    }
}
