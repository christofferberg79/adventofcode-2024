package cberg.aoc2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun day1() {
        assertEquals(11, Day1(Input("day1_ex1")).part1())
        assertEquals(2970687, Day1(Input("day1")).part1())
        assertEquals(31, Day1(Input("day1_ex1")).part2())
        assertEquals(23963899, Day1(Input("day1")).part2())
    }
}