package cberg.aoc2024

class Day19(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val patterns = input[0].split(", ")
    private val designs = input.drop(2)

    fun part1() = designs.count { design -> isPossible(design) }

    private fun isPossible(design: String): Boolean = design.isEmpty() ||
            patterns.any { pattern ->
                design.startsWith(pattern) && isPossible(design.drop(pattern.length))
            }

    fun part2() = designs.sumOf { design -> countSolutions(design) }

    private val cache = mutableMapOf<String, Long>()

    private fun countSolutions(design: String): Long = cache.getOrPut(design) {
        if (design.isEmpty()) {
            1L
        } else {
            patterns.filter { pattern -> design.startsWith(pattern) }
                .sumOf { pattern -> countSolutions(design.drop(pattern.length)) }
        }
    }
}
