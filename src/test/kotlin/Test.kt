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

    @Test
    fun day2() {
        assertEquals(2, Day2(Input("day2_ex1")).part1())
        assertEquals(421, Day2(Input("day2")).part1())
        assertEquals(4, Day2(Input("day2_ex1")).part2())
        assertEquals(476, Day2(Input("day2")).part2())
    }

    @Test
    fun day3() {
        assertEquals(161, Day3(Input("day3_ex1")).part1())
        assertEquals(179571322, Day3(Input("day3")).part1())
        assertEquals(48, Day3(Input("day3_ex2")).part2())
        assertEquals(103811193, Day3(Input("day3")).part2())
    }

    @Test
    fun day4() {
        assertEquals(18, Day4(Input("day4_ex1")).part1())
        assertEquals(2583, Day4(Input("day4")).part1())
        assertEquals(9, Day4(Input("day4_ex1")).part2())
        assertEquals(1978, Day4(Input("day4")).part2())
    }

    @Test
    fun day5() {
        assertEquals(143, Day5(Input("day5_ex1")).part1())
        assertEquals(4135, Day5(Input("day5")).part1())
        assertEquals(123, Day5(Input("day5_ex1")).part2())
        assertEquals(5285, Day5(Input("day5")).part2())
    }

    @Test
    fun day6() {
        assertEquals(41, Day6(Input("day6_ex1")).part1())
        assertEquals(5145, Day6(Input("day6")).part1())
        assertEquals(6, Day6(Input("day6_ex1")).part2())
        assertEquals(1523, Day6(Input("day6")).part2())
    }

    @Test
    fun day7() {
        assertEquals(3749, Day7(Input("day7_ex1")).part1())
        assertEquals(1399219271639, Day7(Input("day7")).part1())
        assertEquals(11387, Day7(Input("day7_ex1")).part2())
        assertEquals(275791737999003, Day7(Input("day7")).part2())
    }

    @Test
    fun day8() {
        assertEquals(14, Day8(Input("day8_ex1")).part1())
        assertEquals(293, Day8(Input("day8")).part1())
        assertEquals(34, Day8(Input("day8_ex1")).part2())
        assertEquals(934, Day8(Input("day8")).part2())
    }

    @Test
    fun day9() {
        assertEquals(1928, Day9(Input("day9_ex1")).part1())
        assertEquals(6446899523367, Day9(Input("day9")).part1())
        assertEquals(2858, Day9(Input("day9_ex1")).part2())
        assertEquals(6478232739671, Day9(Input("day9")).part2())
    }

    @Test
    fun day10() {
        assertEquals(36, Day10(Input("day10_ex1")).part1())
        assertEquals(719, Day10(Input("day10")).part1())
        assertEquals(81, Day10(Input("day10_ex1")).part2())
        assertEquals(1530, Day10(Input("day10")).part2())
    }
}