package cberg.aoc2024

class Day25(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val blocks = input.split { it.isEmpty() }.map { it.toCharGrid() }
    private val positions = blocks.first().positions()

    fun part1() = blocks.withIndex().sumOf { (i, block) ->
        blocks.drop(i + 1).count { other ->
            positions.all { pos -> block[pos] == '.' || other[pos] == '.' }
        }
    }
}
