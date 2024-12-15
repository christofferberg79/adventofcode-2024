package cberg.aoc2024

class Day15(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1(): Int {
        val (mapInput, movesInput) = input.split { line -> line.isEmpty() }
        val map = mapInput.toCharGrid()
        val moves = movesInput.joinToString(separator = "").map { it.toDir() }
        var robot = map.positionOf('@')

        for (move in moves) {
            var next = robot + move
            while (map[next] == 'O') {
                next += move
            }
            if (map[next] == '.') {
                map[robot] = '.'
                robot += move
                if (next != robot) {
                    map[next] = 'O'
                }
                map[robot] = '@'
            }
        }
        return map.positionsOf('O').sumOf { it.gpsCoordinate() }
    }

    private fun Vector.gpsCoordinate() = x + 100 * y

    fun part2(): Int {
        val (mapInput, movesInput) = input.split { line -> line.isEmpty() }
        val map = adjustMap(mapInput.toCharGrid())
        val moves = movesInput.joinToString(separator = "").map { it.toDir() }
        var robot = map.positionOf('@')

        moves@ for (move in moves) {
            val toMove = mutableSetOf(robot)
            val todo = mutableSetOf<Vector>(robot + move)
            while (todo.isNotEmpty()) {
                val pos = todo.first()
                todo -= pos
                when (val c = map[pos]) {
                    '[', ']' -> {
                        val otherHalf = pos + if (c == '[') Dir.E else Dir.W
                        toMove += pos
                        toMove += otherHalf
                        todo += pos + move
                        todo += otherHalf + move
                        todo -= otherHalf
                    }

                    '#' -> continue@moves
                }
            }
            val beforeMove = toMove.associateWith { pos -> map[pos] }
            for (pos in toMove) {
                map[pos] = '.'
            }
            for ((pos, c) in beforeMove) {
                map[pos + move] = c
            }
            robot += move
        }
        return map.positionsOf('[').sumOf { it.gpsCoordinate() }
    }

    private fun Char.toDir(): Vector = when (this) {
        '<' -> Dir.W
        '>' -> Dir.E
        '^' -> Dir.N
        'v' -> Dir.S
        else -> error("Unknown move: $this")
    }

    private fun adjustMap(map: CharGrid): CharGrid = map.map { line ->
        line.flatMap { c ->
            when (c) {
                '.', '#' -> listOf(c, c)
                '@' -> listOf(c, '.')
                'O' -> listOf('[', ']')
                else -> error("Unknown character: $c")
            }
        }.toMutableList()
    }
}
