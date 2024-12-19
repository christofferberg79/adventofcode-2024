package cberg.aoc2024

import cberg.aoc2024.dijkstra.Result
import cberg.aoc2024.dijkstra.dijkstra

class Day16(private val input: CharGrid) {
    constructor(input: Input) : this(input.lines().toCharGrid())

    fun part1() = solve()?.cost ?: "No solution found"

    fun part2() = solve()?.getPathPositions()?.size ?: "No solution found"

    private data class State(val pos: Vector, val dir: Vector)

    private fun State.goForward() = State(pos + dir, dir)
    private fun State.goLeft() = State(pos + dir.turnLeft(), dir.turnLeft())
    private fun State.goRight() = State(pos + dir.turnRight(), dir.turnRight())

    private fun solve() = dijkstra(
        start = State(input.positionOf('S'), Dir.E),
        isGoal = { state -> input[state.pos] == 'E' },
        edges = { state ->
            listOf(state.goForward() to 1, state.goRight() to 1001, state.goLeft() to 1001)
                .filter { (state, _) -> input[state.pos] != '#' }
        }
    )

    private fun Result<State>.getPathPositions() = buildSet {
        val todo = mutableSetOf(this@getPathPositions)
        while (todo.isNotEmpty()) {
            todo.first().let {
                todo -= it
                todo += it.prev
                this += it.node.pos
            }
        }
    }
}