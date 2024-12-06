package cberg.aoc2024

class Day6(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = findGuardPositions(input)?.size ?: error("no solution found")

    private fun findGuardPositions(map: List<String>): Set<Vector>? {
        var dir = Vector(0, -1)

        var pos = startPos(map)

        val positions = mutableSetOf(pos)
        val states = mutableSetOf(pos to dir)

        while (true) {
            val next = pos + dir
            if (next !in map) {
                break
            }
            if (map.charAt(next) == '#') {
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

    private fun startPos(map: List<String>) = map.indices.firstNotNullOf { y ->
        map[y].indexOf('^').let { x -> if (x == -1) null else Vector(x, y) }
    }

    private fun List<String>.charAt(pos: Vector) = this[pos.y][pos.x]
    private operator fun List<String>.contains(v: Vector) = v.y in this.indices && v.x in this[v.y].indices

    private data class Vector(val x: Int, val y: Int) {
        operator fun plus(other: Vector) = Vector(this.x + other.x, this.y + other.y)
        fun turnRight() = Vector(-y, x)
    }

    fun part2(): Int {
        val positions = findGuardPositions(input)!! - startPos(input)
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
