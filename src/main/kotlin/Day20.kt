package cberg.aoc2024

import kotlin.math.abs

class Day20(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    val map = input.toCharGrid()

    fun part1(predicate: (Int) -> Boolean) = findSaves(2).count(predicate)

    fun part2(predicate: (Int) -> Boolean) = findSaves(20).count(predicate)

    private fun findSaves(cheatTime: Int): List<Int> {
        val distLeft = distancesLeft()

        val cheats = cheats(cheatTime)

        return distLeft.flatMap { (pos, dist) ->
            cheats.mapNotNull { cheat ->
                distLeft[pos + cheat]?.let { otherDist -> dist - otherDist - cheat.manhattanDistance }
            }
        }
    }

    private fun distancesLeft(): MutableMap<Vector, Int> {
        val start = map.positionOf('S')
        var pos = map.positionOf('E')
        val distLeft = mutableMapOf(pos to 0)
        var dist = 0
        while (pos != start) {
            pos = Dir.nesw.map { dir -> pos + dir }.single { newPos -> newPos !in distLeft && map[newPos] != '#' }
            distLeft[pos] = ++dist
        }
        return distLeft
    }

    private fun cheats(cheatTime: Int) = (-cheatTime..cheatTime).flatMap { x ->
        (-cheatTime + abs(x)..cheatTime - abs(x)).map { y -> Vector(x, y) }
    }
}
