package cberg.aoc2024

class Day15(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val map: CharGrid
    private val moves: List<Vector>
    private var robot: Vector

    init {
        val (mapInput, movesInput) = input.split { line -> line.isEmpty() }
        map = mapInput.toCharGrid()

        moves = movesInput.joinToString(separator = "").map { it.toDir() }

        robot = map.positionOf('@')
    }

    private fun Char.toDir(): Vector = when (this) {
        '<' -> Dir.W
        '>' -> Dir.E
        '^' -> Dir.N
        'v' -> Dir.S
        else -> error("Unknown move: $this")
    }

    fun part1(): Int {
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
}
