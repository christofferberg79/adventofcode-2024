package cberg.aoc2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun day1() {
        assertEquals(0, Day1(Input("day1_ex1")).part1())
        assertEquals(0, Day1(Input("day1")).part1())
        assertEquals(0, Day1(Input("day1_ex1")).part2())
        assertEquals(0, Day1(Input("day1")).part2())
    }
}