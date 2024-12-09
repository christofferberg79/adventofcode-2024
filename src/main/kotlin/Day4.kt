package cberg.aoc2024

class Day4(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.positions().sumOf { pos -> countXmasAt(pos) }

    private fun countXmasAt(pos: Vector) = Dir.all.count { dir -> getStringAt(pos, dir, 4) == "XMAS" }

    private fun getStringAt(pos: Vector, dir: Vector, length: Int) = buildString {
        for (offset in 0 until length) {
            append(input.charAt(pos + dir * offset) ?: ' ')
        }
    }

    fun part2() = input.positions().count { pos -> hasXmasAt(pos) }

    private fun hasXmasAt(pos: Vector): Boolean {
        val s1 = getStringAt(pos + Dir.NW, Dir.SE, 3)
        val s2 = getStringAt(pos + Dir.SW, Dir.NE, 3)
        return (s1 == "MAS" || s1 == "SAM") && (s2 == "MAS" || s2 == "SAM")
    }
}
