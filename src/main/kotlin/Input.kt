package cberg.aoc2024

import java.io.File

class Input(filename: String) {
    private val file = File("input/$filename")

    fun oneLine() = lines().first()
    fun lines() = file.readLines()
    fun charGrid(): CharGrid = file.readLines().toCharGrid()
    fun <R> lines(transform: (String) -> R) = lines().map(transform)
    fun intLines() = lines().map { it.toInt() }
    fun longLines() = lines().map { it.toLong() }
}