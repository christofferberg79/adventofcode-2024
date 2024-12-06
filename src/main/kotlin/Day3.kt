package cberg.aoc2024

class Day3(private val input: String) {
    constructor(input: Input) : this(input.lines().joinToString(separator = ""))

    private val mulPattern = Regex("""mul\((\d+),(\d+)\)""")
    private val doPattern = Regex("""do\(\)""")
    private val dontPattern = Regex("""don't\(\)""")

    fun part1() = mulPattern.findAll(input).sumOf {
        it.groupValues[1].toInt() * it.groupValues[2].toInt()
    }

    fun part2(): Int {
        var doMul = true
        var sum = 0
        for (i in input.indices) {
            when {
                doPattern.matchesAt(input, i) -> doMul = true
                dontPattern.matchesAt(input, i) -> doMul = false
                doMul -> mulPattern.matchAt(input, i)?.let {
                    sum += it.groupValues[1].toInt() * it.groupValues[2].toInt()
                }
            }
        }
        return sum
    }
}
