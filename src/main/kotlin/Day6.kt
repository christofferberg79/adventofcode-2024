package cberg.aoc2024

class Day6(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = findGuardPositions(input)?.size ?: error("no solution found")

    private fun findGuardPositions(map: List<String>): Set<Vector>? {
        var pos = input.positionOf('^')
        var dir = Dir.N

        val positions = mutableSetOf(pos)
        val states = mutableSetOf(pos to dir)

        while (true) {
            val next = pos + dir
            if (next !in map) {
                break
            }
            if (map[next] == '#') {
                dir = dir.turnRight()
            } else {
                pos = next
                positions += pos
            }
            if (!states.add(pos to dir)) {
                return null
            }
        }

        return positions
    }

    fun part2(): Int {
        val positions = findGuardPositions(input)!! - input.positionOf('^')
        return positions.count { pos -> findGuardPositions(input.withObstacleAt(pos)) == null }
    }

    private fun List<String>.withObstacleAt(pos: Vector) = mapIndexed { y, line ->
        if (y == pos.y) {
            line.replaceRange(pos.x, pos.x + 1, "#")
        } else {
            line
        }
    }
}
