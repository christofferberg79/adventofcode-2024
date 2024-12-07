package cberg.aoc2024

class Day7(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.map { parse(it) }.filter { it.isSolvable() }.sumOf { it.testValue }

    private fun parse(line: String) = Equation(
        testValue = line.substringBefore(':').toLong(),
        numbers = line.substringAfter(": ").split(" ").map { it.toLong() })

    private data class Equation(val testValue: Long, val numbers: List<Long>)

    private fun Equation.isSolvable() = find(testValue, numbers.first(), numbers.drop(1))

    private fun find(testValue: Long, acc: Long, numbers: List<Long>): Boolean = when {
        numbers.isEmpty() -> acc == testValue
        else -> find(testValue, acc + numbers.first(), numbers.drop(1)) ||
                find(testValue, acc * numbers.first(), numbers.drop(1))
    }

    fun part2() = input.map { parse(it) }.filter { it.isSolvable2() }.sumOf { it.testValue }

    private fun Equation.isSolvable2() = find2(testValue, numbers.first(), numbers.drop(1))

    private fun find2(testValue: Long, acc: Long, numbers: List<Long>): Boolean = when {
        numbers.isEmpty() -> acc == testValue
        else -> find2(testValue, acc + numbers.first(), numbers.drop(1)) ||
                find2(testValue, acc * numbers.first(), numbers.drop(1)) ||
                find2(testValue, acc concat numbers.first(), numbers.drop(1))
    }

    private infix fun Long.concat(other: Long): Long = "${this}${other}".toLong()
}
