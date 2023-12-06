package day6

import java.io.File

fun main() {
    val fileName = "day6/input.txt"
    val file = File(fileName)
    val times = file.readText().split("\n")[0].split(Regex("\\s+")).subList(1, 5).map { it.toInt() }
    val distances = file.readText().split("\n")[1].split(Regex("\\s+")).subList(1, 5).map { it.toInt() }

    println(times)
    println(distances)

    var result = 1
    for (i in times.indices) {
        val time = times[i]
        val distance = distances[i]
        var wins = 0
        for (hold in 0..<time) {
            if ((time - hold) * hold >= distance) {
                wins++
            }
        }
        result *= wins
    }
    println(result)
}
