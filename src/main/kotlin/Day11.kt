package cberg.aoc2024

class Day11(private val input: String) {
    constructor(input: Input) : this(input.oneLine())

    fun part1() = solve(25)

    fun part2() = solve(75)

    private fun solve(blinks: Int): Long = input.split(" ").sumOf { count(it.toLong(), blinks) }

    private val cache = mutableMapOf<Pair<Long, Int>, Long>()

    private fun count(stone: Long, blinks: Int): Long = cache.getOrPut(stone to blinks) {
        when {
            blinks == 0 -> 1
            stone == 0L -> count(1, blinks - 1)
            stone.toString().length % 2 == 0 -> stone.toString().let { s ->
                count(s.take(s.length / 2).toLong(), blinks - 1) +
                        count(s.takeLast(s.length / 2).toLong(), blinks - 1)
            }

            else -> count(stone * 2024, blinks - 1)
        }
    }
}
