package cberg.aoc2024

class Day23(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    val connections = input.flatMap { it.split("-").let { (c1, c2) -> listOf(c1 to c2, c2 to c1) } }
        .groupBy(keySelector = { it.first }, valueTransform = { it.second })
        .withDefault { emptyList() }

    fun part1(): Int {
        val triplets = mutableSetOf<Set<String>>()

        for (k1 in connections.keys) {
            for (k2 in connections.getValue(k1)) {
                for (k3 in connections.getValue(k2)) {
                    if (k1 in connections.getValue(k3)) {
                        triplets.add(setOf(k1, k2, k3))
                    }
                }
            }
        }

        return triplets.count { it.any { it.startsWith('t') } }
    }

    fun part2(): String {
        var clusters = connections.map { (k, v) -> (v + k) }

        while (true) {
            val largestCluster = clusters.firstOrNull { cluster ->
                cluster.all { c ->
                    cluster.all { c2 -> c2 == c || c2 in connections.getValue(c) }
                }
            }
            if (largestCluster != null) {
                return largestCluster.toList().sorted().joinToString(",")
            } else {
                clusters = clusters.flatMap { cluster -> cluster.map { c -> cluster - c } }
            }
        }
    }
}
