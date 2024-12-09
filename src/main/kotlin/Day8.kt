package cberg.aoc2024

class Day8(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = pairs().map { (a, b) -> b + (b - a) }
        .toSet().count { it in input }

    fun part2() = pairs().flatMap { (a, b) ->
        generateSequence(b) { pos -> pos + (b - a) }.takeWhile { it in input }
    }
        .toSet().count()

    private fun pairs() = input.flatMapIndexed { y, line -> line.mapIndexed { x, c -> Vector(x, y) to c } }
        .filter { (_, c) -> c != '.' }
        .groupBy(keySelector = { it.second }, valueTransform = { it.first }).values.flatMap { positions ->
            positions.flatMap { a ->
                positions.filter { b -> a != b }
                    .map { b -> a to b }
            }
        }

}
