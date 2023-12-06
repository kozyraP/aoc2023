package day4

import java.io.File

fun main() {
    val fileName = "day4/input.txt"
    val file = File(fileName)
    val lines = ArrayList<String>()
    file.forEachLine { lines.add(it) }

    val map = mutableMapOf<Int, Int>()
    for (i in lines.indices) {
        map[i] = 1
    }
    for (i in lines.indices) {
        val items = lines[i].split(Regex("[:|]"))
        val winning = items[1].trim().split(Regex("\\s+")).map { s -> s.trim().toInt() }
        val having = items[2].trim().split(Regex("\\s+")).map { s -> s.trim().toInt() }
        var counter = 0
        for (w in winning) {
            if (having.contains(w)) {
                counter++
            }
        }
        for (n in (i + 1)..(i + counter)) {
            map[n] = map.getOrDefault(n, 1) + map[i]!!
        }
    }
    println(map.values.sum())
}
