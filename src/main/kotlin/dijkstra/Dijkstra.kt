package cberg.aoc2024.dijkstra

data class Result<N>(val node: N, val cost: Int, val prev: List<Result<N>>)

fun <N> dijkstra(
    start: N,
    isGoal: (node: N) -> Boolean,
    edges: (fromNode: N) -> List<Pair<N, Int>>
): Result<N>? {
    val todo = mutableMapOf<N, Result<N>>(start to Result(start, 0, emptyList()))
    val done = mutableMapOf<N, Result<N>>()
    while (todo.isNotEmpty()) {
        val (node, admin) = todo.minBy { it.value.cost }
        todo -= node
        done[node] = admin
        if (isGoal(node)) {
            return admin
        }
        edges(node).filter { it.first !in done }.forEach { (toNode, cost) ->
            val newCost = admin.cost + cost
            val oldAdmin = todo[toNode]
            if (oldAdmin == null || newCost < oldAdmin.cost) {
                todo[toNode] = Result(toNode, newCost, listOf(admin))
            } else if (newCost == oldAdmin.cost) {
                todo[toNode] = Result(toNode, newCost, oldAdmin.prev + admin)
            }
        }
    }
    return null
}

fun <N> dijkstra(start: N, goal: N, edges: (from: N) -> List<Pair<N, Int>>) = dijkstra(start, { n -> n == goal }, edges)
