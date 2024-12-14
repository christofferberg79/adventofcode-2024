package cberg.aoc2024

class Day14(private val width: Int, private val height: Int, input: List<String>) {
    constructor(width: Int, height: Int, input: Input) : this(width, height, input.lines())

    data class Robot(val p: Vector, val v: Vector)

    private fun Robot.move(s: Int = 1) = Robot((p + v * s).wrap(), v)

    val pattern = Regex("""p=(-?\d+),(-?\d+) v=(-?\d+),(-?\d+)""")

    val robots = input.map { line ->
        pattern.matchEntire(line)?.groupValues
            ?.drop(1)?.map { it.toInt() }
            ?.let { (px, py, vx, vy) -> Robot(Vector(px, py), Vector(vx, vy)) }
            ?: error("can't parse $line")
    }

    fun part1(): Int {
        val newRobots = robots.map { robot -> robot.move(100) }
        val q1 = newRobots.count { it.p.x < width / 2 && it.p.y < height / 2 }
        val q2 = newRobots.count { it.p.x < width / 2 && it.p.y > height / 2 }
        val q3 = newRobots.count { it.p.x > width / 2 && it.p.y < height / 2 }
        val q4 = newRobots.count { it.p.x > width / 2 && it.p.y > height / 2 }
        return q1 * q2 * q3 * q4
    }

    private fun Vector.wrap() = Vector(x.mod(width), y.mod(height))

    fun part2(): Int {
        val christmasTree = listOf(
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            "X                             X",
            "X                             X",
            "X                             X",
            "X                             X",
            "X              X              X",
            "X             XXX             X",
            "X            XXXXX            X",
            "X           XXXXXXX           X",
            "X          XXXXXXXXX          X",
            "X            XXXXX            X",
            "X           XXXXXXX           X",
            "X          XXXXXXXXX          X",
            "X         XXXXXXXXXXX         X",
            "X        XXXXXXXXXXXXX        X",
            "X          XXXXXXXXX          X",
            "X         XXXXXXXXXXX         X",
            "X        XXXXXXXXXXXXX        X",
            "X       XXXXXXXXXXXXXXX       X",
            "X      XXXXXXXXXXXXXXXXX      X",
            "X        XXXXXXXXXXXXX        X",
            "X       XXXXXXXXXXXXXXX       X",
            "X      XXXXXXXXXXXXXXXXX      X",
            "X     XXXXXXXXXXXXXXXXXXX     X",
            "X    XXXXXXXXXXXXXXXXXXXXX    X",
            "X             XXX             X",
            "X             XXX             X",
            "X             XXX             X",
            "X                             X",
            "X                             X",
            "X                             X",
            "X                             X",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
        )

        return generateSequence(robots) { robots -> robots.map { robot -> robot.move() } }
            .indexOfFirst { robots -> christmasTree in robots }
    }

    private operator fun List<Robot>.contains(picture: List<String>): Boolean {
        val robotPositions = this.map { robot -> robot.p }.toSet()
        val offsets = (0..width - picture.maxOf(String::length))
            .flatMap { x -> (0..height - picture.size).map { y -> Vector(x, y) } }
        val picturePositions = picture.indices.flatMap { y -> picture[y].indices.map { x -> Vector(x, y) } }
        return offsets.any { offset ->
            picturePositions.all { p -> (picture.charAt(p) == 'X') == (p + offset in robotPositions) }
        }
    }
}
