package cberg.aoc2024

class Day4(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.indices.sumOf { row -> input[row].indices.sumOf { col -> countXmasAt(row, col) } }

    private fun countXmasAt(row: Int, col: Int): Int {
        val offsets = listOf(0 to 1, 1 to 1, 1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1)
        return offsets.count { (dRow, dCol) -> getStringAt(row, col, dRow, dCol, 4) == "XMAS" }
    }

    private fun getStringAt(row: Int, col: Int, dRow: Int, dCol: Int, length: Int) = buildString {
        for (offset in 0 until length) {
            append(input.getCharAt(row + dRow * offset, col + dCol * offset))
        }
    }

    private fun List<String>.getCharAt(row: Int, col: Int) = getOrNull(row)?.getOrNull(col) ?: ' '

    fun part2() = input.indices.sumOf { row -> input[row].indices.count { col -> hasXmasAt(row, col) } }

    private fun hasXmasAt(row: Int, col: Int): Boolean {
        val s1 = getStringAt(row-1, col-1, 1, 1, 3)
        val s2 = getStringAt(row-1, col+1, 1, -1, 3)
        return (s1 == "MAS" || s1 == "SAM") && (s2 == "MAS" || s2 == "SAM")
    }
}
