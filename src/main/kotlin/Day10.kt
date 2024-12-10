package cberg.aoc2024

class Day10(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.positionsOf('0').sumOf { pos -> findNines(pos).toSet().size }

    fun part2() = input.positionsOf('0').sumOf { pos -> findNines(pos).size }

    private fun findNines(pos: Vector): List<Vector> = when (val height = input[pos]) {
        '9' -> listOf(pos)
        else -> Dir.nesw.map { dir -> pos + dir }
            .filter { newPos -> input.charAt(newPos) == height + 1 }
            .flatMap { newPos -> findNines(newPos) }
    }
}