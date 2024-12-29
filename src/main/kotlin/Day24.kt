package cberg.aoc2024

class Day24(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private data class Gate(val in1: String, val op: String, val in2: String, val out: String)

    private val wires: MutableMap<String, Boolean>
    private val gates: Map<String, Gate>

    init {
        val (inputLines, networkLines) = input.split { it.isEmpty() }

        wires = inputLines.associate { line ->
            line.split(": ").let { (wire, value) -> wire to (value == "1") }
        }.toMutableMap()

        gates = networkLines.associate { line ->
            line.split(" ").let { (in1, op, in2, _, out) -> out to Gate(in1, op, in2, out) }
        }
    }

    fun part1() = gates.keys.filter { it.startsWith('z') }.sorted().map { valueOf(it) }
        .fold(1L to 0L) { (factor, sum), value -> (factor * 2) to if (value) (sum + factor) else sum }.second

    private fun valueOf(wire: String): Boolean = wires.getOrPut(wire) {
        val gate = gates[wire] ?: error("No gate for $wire")
        return when (gate.op) {
            "AND" -> valueOf(gate.in1) && valueOf(gate.in2)
            "OR" -> valueOf(gate.in1) || valueOf(gate.in2)
            "XOR" -> valueOf(gate.in1) != valueOf(gate.in2)
            else -> error("Unknown operator ${gate.op}")
        }
    }

    fun part2() = 0
}
