package cberg.aoc2024

class Day5(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val rules = input.takeWhile { it.isNotBlank() }
        .map { it.split("|").let { (a, b) -> a.toInt() to b.toInt() } }.toSet()

    private val updates = input.takeLastWhile { it.isNotBlank() }.map { it.split(",").map { it.toInt() } }

    fun part1() = updates.filter { it.isCorrect() }.sumOf { it.middle() }

    private fun List<Int>.isCorrect(): Boolean {
        for (i in 0 until lastIndex) {
            for (j in i + 1..lastIndex) {
                if (rules.contains(this[j] to this[i])) {
                    return false
                }
            }
        }
        return true
    }

    private fun List<Int>.middle() = get(lastIndex / 2)

    fun part2() = updates.filterNot { it.isCorrect() }.sumOf { it.ordered().middle() }

    private fun List<Int>.ordered() = sortedWith { o1, o2 ->
        when {
            rules.contains(o1 to o2) -> -1
            rules.contains(o2 to o1) -> 1
            else -> 0
        }
    }
}
