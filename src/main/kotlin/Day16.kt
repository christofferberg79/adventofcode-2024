package cberg.aoc2024

class Day16(private val input: CharGrid) {
    constructor(input: Input) : this(input.lines().toCharGrid())

    fun part1() = solve().let { (done, end) -> done[end]!!.cost }

    fun part2() = solve().let { (done, end) -> getPathPositions(done, end).size }

    private fun solve(): Pair<Map<State, Admin>, State> {
        val todo = mutableMapOf(State(input.positionOf('S'), Dir.E) to Admin())
        val done = mutableMapOf<State, Admin>()
        while (todo.isNotEmpty()) {
            val (state, admin) = todo.minBy { (_, admin) -> admin.cost }
            todo.remove(state)
            done[state] = admin
            if (input[state.pos] == 'E') {
                return done to state
            }
            getPossiblePaths(state).filter { (newState, _) -> newState !in done }
                .forEach { (newState, newCost) ->
                    todo.merge(newState, Admin(admin.cost + newCost, mutableListOf(state))) { old, new ->
                        if (old.cost < new.cost) {
                            old
                        } else if (new.cost < old.cost) {
                            new
                        } else {
                            Admin(old.cost, old.prev + new.prev)
                        }
                    }
                }
        }
        error("No solution found")
    }

    private data class State(val pos: Vector, val dir: Vector)

    private fun State.stepForward() = State(pos + dir, dir)
    private fun State.turnLeft() = State(pos, dir.turnLeft())
    private fun State.turnRight() = State(pos, dir.turnRight())

    private fun getPossiblePaths(state: State) = listOf(
        state to 0,
        state.turnRight() to 1000,
        state.turnLeft() to 1000
    )
        .map { (state, cost) -> state.stepForward() to (cost + 1) }
        .filter { (state, _) -> input[state.pos] != '#' }

    private data class Admin(val cost: Int = 0, val prev: List<State> = emptyList<State>())

    private fun getPathPositions(done: Map<State, Admin>, end: State) = buildSet {
        val todo = mutableListOf(end)
        while (todo.isNotEmpty()) {
            todo.removeFirst().let { state ->
                add(state.pos)
                todo.addAll(done[state]?.prev ?: emptyList<State>())
            }
        }
    }
}