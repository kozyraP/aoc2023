package day4

import java.io.File
import kotlin.math.pow

fun main() {
    val fileName = "day4/input.txt"
    val file = File(fileName)
    val lines = ArrayList<String>()
    file.forEachLine { lines.add(it) }
    var result = 0
    for (line in lines) {
        val items = line.split(Regex("[:|]"))
        val winning = items[1].trim().split(Regex("\\s+")).map { s -> s.trim().toInt() }
        val having = items[2].trim().split(Regex("\\s+")).map { s -> s.trim().toInt() }
        var counter = -1
        for (w in winning) {
            if (having.contains(w)) {
                counter++
            }
        }
        if (counter >= 0) {
            result += 2.0.pow(counter).toInt()
        }
    }
    println(result)
}
