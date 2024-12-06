package cberg.aoc2024

import kotlin.math.abs

class Day1(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val lists = input.map { it.split("   ").map { it.toInt() } }.map { (a, b) -> a to b }.unzip()

    fun part1() = (lists.first.sorted() zip lists.second.sorted()).sumOf { abs(it.first - it.second) }

    fun part2(): Int {
        val frequency = lists.second.groupingBy { it }.eachCount()
        return lists.first.sumOf { it * (frequency[it] ?: 0) }
    }
}