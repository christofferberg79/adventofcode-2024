package cberg.aoc2024

data class Vector(val x: Int, val y: Int)

operator fun Vector.plus(other: Vector) = Vector(this.x + other.x, this.y + other.y)
operator fun Vector.minus(other: Vector) = Vector(this.x - other.x, this.y - other.y)
operator fun Vector.times(factor: Int) = Vector(this.x * factor, this.y * factor)
operator fun Vector.unaryMinus() = Vector(-x, -y)
fun Vector.turnRight() = Vector(-y, x)
fun Vector.turnLeft() = Vector(y, -x)

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

typealias Grid<E> = List<MutableList<E>>
typealias CharGrid = Grid<Char>

fun List<String>.toCharGrid(): CharGrid = map { s -> s.toMutableList() }

fun <E> Grid<E>.positions() = buildList {
    for (y in this@positions.indices)
        for (x in this@positions[y].indices)
            add(Vector(x, y))
}

fun <E> Grid<E>.positionsOf(element: E) = buildList {
    for (y in this@positionsOf.indices)
        for (x in this@positionsOf[y].indices)
            if (this@positionsOf[y][x] == element)
                add(Vector(x, y))
}

fun <E> Grid<E>.positionOf(element: E): Vector {
    for (y in this.indices)
        for (x in this[y].indices)
            if (this[y][x] == element)
                return Vector(x, y)
    throw NoSuchElementException()
}

operator fun <E> Grid<E>.contains(v: Vector) = v.y in indices && v.x in get(v.y).indices

operator fun <E> Grid<E>.get(v: Vector) = this[v.y][v.x]
fun <E> Grid<E>.getOrNull(v: Vector) = getOrNull(v.y)?.getOrNull(v.x)
operator fun <E> Grid<E>.set(v: Vector, e: E) {
    this[v.y][v.x] = e
}

fun <T> Iterable<T>.split(delimiter: (T) -> Boolean): List<List<T>> {
    val lists = mutableListOf(mutableListOf<T>())
    for (item in this) {
        if (delimiter(item)) {
            lists.add(mutableListOf())
        } else {
            lists.last() += item
        }
    }
    return lists
}

 fun <T> List<T>.binarySearch(fromIndex: Int = 0, toIndex: Int = size, predicate: (T) -> Boolean): T {
    var i = fromIndex-1
    var j = toIndex
    while (i + 1 < j) {
        val m = i + (j - i) / 2
        if (predicate(this[m])) {
            j = m
        } else {
            i = m
        }
    }
    return this[j]
}
