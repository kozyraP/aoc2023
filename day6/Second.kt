package day6

import java.io.File

fun main() {
    val fileName = "day6/input.txt"
    val file = File(fileName)
    val time = file.readText().split("\n")[0].split(":")[1].replace(" ", "").toLong()
    val distance = file.readText().split("\n")[1].split(":")[1].replace(" ", "").toLong()
    var wins = 0
    for (hold in 0..<time) {
        if ((time - hold) * hold >= distance) {
            wins++
        }
    }
    println(wins)
}
