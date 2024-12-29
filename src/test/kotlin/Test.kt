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

    @Test
    fun day11() {
        assertEquals(55312, Day11(Input("day11_ex1")).part1())
        assertEquals(184927, Day11(Input("day11")).part1())
        assertEquals(220357186726677, Day11(Input("day11")).part2())
    }

    @Test
    fun day12() {
        assertEquals(1930, Day12(Input("day12_ex1")).part1())
        assertEquals(1522850, Day12(Input("day12")).part1())
        assertEquals(1206, Day12(Input("day12_ex1")).part2())
        assertEquals(953738, Day12(Input("day12")).part2())
    }

    @Test
    fun day13() {
        assertEquals(480, Day13(Input("day13_ex1")).part1())
        assertEquals(31897, Day13(Input("day13")).part1())
        assertEquals(87596249540359, Day13(Input("day13")).part2())
    }

    @Test
    fun day14() {
        assertEquals(12, Day14(11, 7, Input("day14_ex1")).part1())
        assertEquals(208437768, Day14(101, 103, Input("day14")).part1())
        assertEquals(7492, Day14(101, 103, Input("day14")).part2())
    }

    @Test
    fun day15() {
        assertEquals(10092, Day15(Input("day15_ex1")).part1())
        assertEquals(1471826, Day15(Input("day15")).part1())
        assertEquals(9021, Day15(Input("day15_ex1")).part2())
        assertEquals(1457703, Day15(Input("day15")).part2())
    }

    @Test
    fun day16() {
        assertEquals(7036, Day16(Input("day16_ex1")).part1())
        assertEquals(11048, Day16(Input("day16_ex2")).part1())
        assertEquals(105508, Day16(Input("day16")).part1())
        assertEquals(45, Day16(Input("day16_ex1")).part2())
        assertEquals(64, Day16(Input("day16_ex2")).part2())
        assertEquals(548, Day16(Input("day16")).part2())
    }

    @Test
    fun day17() {
        assertEquals("4,6,3,5,6,3,5,2,1,0", Day17(Input("day17_ex1")).part1())
        assertEquals("2,0,7,3,0,3,1,3,7", Day17(Input("day17")).part1())
    }

    @Test
    fun day18() {
        assertEquals(22, Day18(0..6, Input("day18_ex1")).part1(12))
        assertEquals(340, Day18(0..70, Input("day18")).part1(1024))
        assertEquals("6,1", Day18(0..6, Input("day18_ex1")).part2(12))
        assertEquals("34,32", Day18(0..70, Input("day18")).part2(1024))
    }

    @Test
    fun day19() {
        assertEquals(6, Day19(Input("day19_ex1")).part1())
        assertEquals(216, Day19(Input("day19")).part1())
        assertEquals(16, Day19(Input("day19_ex1")).part2())
        assertEquals(603191454138773, Day19(Input("day19")).part2())
    }

    @Test
    fun day20() {
        assertEquals(14, Day20(Input("day20_ex1")).part1 { it == 2 })
        assertEquals(3, Day20(Input("day20_ex1")).part1 { it == 12 })
        assertEquals(1, Day20(Input("day20_ex1")).part1 { it == 64 })
        assertEquals(1378, Day20(Input("day20")).part1 { it >= 100 })
        assertEquals(32, Day20(Input("day20_ex1")).part2 { it == 50 })
        assertEquals(19, Day20(Input("day20_ex1")).part2 { it == 64 })
        assertEquals(3, Day20(Input("day20_ex1")).part2 { it == 76 })
        assertEquals(975379, Day20(Input("day20")).part2 { it >= 100 })
    }

    @Test
    fun day22() {
        assertEquals(37327623, Day22(Input("day22_ex1")).part1())
        assertEquals(20332089158, Day22(Input("day22")).part1())
        assertEquals(23, Day22(Input("day22_ex2")).part2())
        assertEquals(2191, Day22(Input("day22")).part2())
    }

    @Test
    fun day23() {
        assertEquals(7, Day23(Input("day23_ex1")).part1())
        assertEquals(1330, Day23(Input("day23")).part1())
        assertEquals("co,de,ka,ta", Day23(Input("day23_ex1")).part2())
        assertEquals("hl,io,ku,pk,ps,qq,sh,tx,ty,wq,xi,xj,yp", Day23(Input("day23")).part2())
    }

    @Test
    fun day24() {
        assertEquals(2024, Day24(Input("day24_ex1")).part1())
        assertEquals(42883464055378, Day24(Input("day24")).part1())
    }
}