package cberg.aoc2024

class Day17(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private var a = input[0].substringAfter("Register A: ").toInt()
    private var b = input[1].substringAfter("Register B: ").toInt()
    private var c = input[2].substringAfter("Register C: ").toInt()
    private val program = input[4].substringAfter("Program: ").split(",").map { it.toInt() }
    private var pointer = 0
    val output = mutableListOf<Int>()

    fun part1(): String {
        while (pointer in program.indices) {
            pointer = step()
        }
        return output.joinToString(",")
    }

    private fun step(): Int {
        when (program[pointer]) {
            0 -> a = a shr combo
            1 -> b = b xor literal
            2 -> b = combo % 8
            3 -> if (a != 0) return literal
            4 -> b = b xor c
            5 -> output += combo % 8
            6 -> b = a shr combo
            7 -> c = a shr combo
        }
        return pointer + 2
    }

    private val literal: Int get() = program[pointer + 1]

    private val combo: Int
        get() = when (val operand = program[pointer + 1]) {
            0, 1, 2, 3 -> operand
            4 -> a
            5 -> b
            6 -> c
            else -> error("Invalid operand $operand")
        }

    fun part2() = 0
}
