package cberg.aoc2024

class Day6(private val input: CharGrid) {
    constructor(input: Input) : this(input.charGrid())

    fun part1() = findGuardPositions(input)?.size ?: error("no solution found")

    private fun findGuardPositions(map: CharGrid): Set<Vector>? {
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

    private fun CharGrid.withObstacleAt(pos: Vector): CharGrid = mapIndexed { y, line ->
        if (y == pos.y) {
            buildList { addAll(line); this[pos.x] = '#' }.toMutableList()
        } else {
            line
        }
    }
}
