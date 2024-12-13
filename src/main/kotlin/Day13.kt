package cberg.aoc2024

class Day13(private val input: List<List<String>>) {
    constructor(input: Input) : this(input.lines().split { it.isEmpty() })

    val buttonPattern = Regex("""Button .: X\+(\d+), Y\+(\d+)""")
    val pricePattern = Regex("""Prize: X=(\d+), Y=(\d+)""")

    fun part1() = solve(maxPresses = 100)

    fun part2() = solve(add = 10000000000000)

    private fun solve(add: Long = 0L, maxPresses: Long = Long.MAX_VALUE): Long {
        var tokens = 0L
        input.forEach { (buttonA, buttonB, price) ->
            val (ax, ay) = parseButton(buttonA)
            val (bx, by) = parseButton(buttonB)
            val (x, y) = parsePrice(price).map { it + add }

            val det = by * ax - bx * ay
            val ad = by * x - bx * y
            val bd = ax * y - ay * x
            if (ad % det == 0L && bd % det == 0L) {
                val a = ad / det
                val b = bd / det

                if (a in 0..maxPresses && b in 0..maxPresses) {
                    tokens += 3 * a + b
                }
            }
        }
        return tokens
    }

    private fun parseButton(s: String) =
        buttonPattern.matchEntire(s)?.groupValues?.drop(1)?.map { it.toInt() }
            ?: error("Could not parse $s")

    private fun parsePrice(s: String) =
        pricePattern.matchEntire(s)?.groupValues?.drop(1)?.map { it.toLong() }
            ?: error("Could not parse $s")
}