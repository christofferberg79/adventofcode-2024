package cberg.aoc2024

class Day8(private val input: CharGrid) {
    constructor(input: Input) : this(input.charGrid())

    fun part1() = pairs().map { (a, b) -> b + (b - a) }
        .toSet().count { it in input }

    fun part2() = pairs().flatMap { (a, b) ->
        generateSequence(b) { pos -> pos + (b - a) }.takeWhile { it in input }
    }
        .toSet().count()

    private fun pairs() = input.positions().filter { input[it] != '.' }.let { positions ->
        positions.flatMap { a ->
            positions.filter { b -> b != a && input[b] == input[a] }.map { b -> a to b }
        }
    }
}
