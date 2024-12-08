package cberg.aoc2024

class Day8(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.flatMapIndexed { y, line -> line.mapIndexed { x, c -> Vector(x, y) to c } }
        .filter { (_, c) -> c != '.' }
        .groupBy(keySelector = { it.second }, valueTransform = { it.first })
        .values.flatMap { positions ->
            positions.flatMap { a ->
                positions.filter { b -> a != b }.map { b ->
                    a + a - b
                }
            }
        }.toSet().count { (x, y) -> y in input.indices && x in input[y].indices }

    fun part2() = input.flatMapIndexed { y, line -> line.mapIndexed { x, c -> Vector(x, y) to c } }
        .filter { (_, c) -> c != '.' }
        .groupBy(keySelector = { it.second }, valueTransform = { it.first })
        .values.flatMap { positions ->
            positions.flatMap { a ->
                positions.filter { b -> a != b }.flatMap { b ->
                    generateSequence(b) { x -> x + b - a }.takeWhile { (x, y) -> y in input.indices && x in input[y].indices }
                }
            }
        }.toSet().count()

    private data class Vector(val x: Int, val y: Int) {
        operator fun plus(other: Vector) = Vector(this.x + other.x, this.y + other.y)
        operator fun minus(other: Vector) = Vector(this.x - other.x, this.y - other.y)
    }
}
