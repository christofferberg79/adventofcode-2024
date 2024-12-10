package cberg.aoc2024

data class Vector(val x: Int, val y: Int)

operator fun Vector.plus(other: Vector) = Vector(this.x + other.x, this.y + other.y)
operator fun Vector.minus(other: Vector) = Vector(this.x - other.x, this.y - other.y)
operator fun Vector.times(factor: Int) = Vector(this.x * factor, this.y * factor)
fun Vector.turnRight() = Vector(-y, x)

object Dir {
    val N = Vector(0, -1)
    val NE = Vector(1, -1)
    val E = Vector(1, 0)
    val SE = Vector(1, 1)
    val S = Vector(0, 1)
    val SW = Vector(-1, 1)
    val W = Vector(-1, 0)
    val NW = Vector(-1, -1)
    val all = listOf(N, NE, E, SE, S, SW, W, NW)
    val nesw = listOf(N, E, S, W)
}

fun List<String>.charAt(v: Vector) = getOrNull(v.y)?.getOrNull(v.x)
operator fun List<String>.get(v: Vector) = this[v.y][v.x]
fun List<String>.positions() = indices.flatMap { row -> get(row).indices.map { col -> Vector(col, row) } }
operator fun List<String>.contains(v: Vector) = v.y in indices && v.x in get(v.y).indices
fun List<String>.positionOf(c: Char) = indices.firstNotNullOf { y ->
    get(y).indexOf(c).let { x -> if (x == -1) null else Vector(x, y) }
}
fun List<String>.positionsOf(c: Char) = positions().filter { charAt(it) == c }


