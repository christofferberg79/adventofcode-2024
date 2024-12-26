package cberg.aoc2024

class Day22(private val input: List<Long>) {
    constructor(input: Input) : this(input.longLines())

    fun part1() = input.sumOf { sequence(it).elementAt(2000) }

    private fun sequence(lng: Long): Sequence<Long> = generateSequence(lng) { nextNumber(it) }

    private fun nextNumber(n: Long) = n.step { it * 64 }.step { it / 32 }.step { it * 2048 }

    private fun Long.step(function: (Long) -> Long) = (function(this) xor this) % 16777216

    fun part2(): Long {
        val sum = mutableMapOf<List<Long>, Long>()
        input.forEach { start ->
            val first = mutableMapOf<List<Long>, Long>()
            sequence(start).map { it % 10 }
                .windowed(2).take(2000).map { (n1, n2) -> n2 to (n2 - n1) }
                .windowed(4).map { four -> four.map { it.second } to four.last().first }
                .forEach { (k, v) -> first.putIfAbsent(k, v) }
            first.forEach { (k, v) -> sum.merge(k, v, Long::plus) }
        }
        return sum.maxOf { it.value }
    }
}
