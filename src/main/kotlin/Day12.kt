package cberg.aoc2024

class Day12(private val input: CharGrid) {
    constructor(input: Input) : this(input.charGrid())

    fun part1() = solve(::perimeterPriceBySize)

    private fun solve(perimeterPrice: (Set<Pair<Vector, Vector>>) -> Int): Int {
        val plots = input.positions().toMutableSet()
        var price = 0
        while (plots.isNotEmpty()) {
            val region = mutableSetOf<Vector>()
            val perimeter = mutableSetOf<Pair<Vector, Vector>>()
            val todo = mutableSetOf(plots.first())
            while (todo.isNotEmpty()) {
                val plot = todo.first()
                for (dir in Dir.nesw) {
                    val otherPlot = plot + dir
                    if (otherPlot in region) {
                        continue
                    } else if (input.getOrNull(otherPlot) == input.getOrNull(plot)) {
                        todo += otherPlot
                    } else {
                        perimeter += plot to dir
                    }
                }
                region += plot
                plots -= plot
                todo -= plot
            }
            price += region.size * perimeterPrice(perimeter)
        }
        return price
    }

    fun perimeterPriceBySize(perimeter: Set<Pair<Vector, Vector>>) = perimeter.size

    fun part2() = solve(::perimeterPriceBySides)

    fun perimeterPriceBySides(perimeter: Set<Pair<Vector, Vector>>): Int {
        var sides = 0
        perimeter.groupBy { (_, dir) -> dir }
            .mapValues { (_, values) -> values.map { (plot, _) -> plot }.toMutableSet() }
            .forEach { (dir, plots) ->
                while (plots.isNotEmpty()) {
                    sides++
                    val start = plots.first()
                    plots -= start
                    plots -= plots.adjacentPlots(start, dir.turnLeft())
                    plots -= plots.adjacentPlots(start, dir.turnRight())
                }
            }
        return sides
    }

    private fun Set<Vector>.adjacentPlots(start: Vector, dir: Vector) =
        generateSequence(start) { pos -> pos + dir }.drop(1).takeWhile { it in this }.toList()
}
