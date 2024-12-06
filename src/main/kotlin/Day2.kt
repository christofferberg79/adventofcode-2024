package cberg.aoc2024

class Day2(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val reports = input.map { line -> line.split(" ").map { it.toInt() } }

    fun part1() = reports.count { report -> report.isSafe() }

    private fun List<Int>.isSafe() = zipWithNext().map { (a, b) -> a - b }.let { diffs ->
        diffs.all { it in 1..3 } || diffs.all { it in -3..-1 }
    }

    fun part2() = reports.count { report -> report.variants().any { it.isSafe() } }

    private fun <E> List<E>.variants(): List<List<E>> {
        return indices.map { i -> take(i) + takeLast(lastIndex - i) }
    }
}
