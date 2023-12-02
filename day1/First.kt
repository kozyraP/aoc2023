package day1

import java.io.File

fun main() {
    val fileName = "day1/input.txt"
    val file = File(fileName)
    val lines = ArrayList<String>()
    file.forEachLine { lines.add(it) }
    var sum = 0
    for (line in lines) {
        var temp = ""
        for (c in line) {
            if (c.isDigit()) {
                temp += c
                break
            }
        }
        for (c in line.reversed()) {
            if (c.isDigit()) {
                temp += c
                break
            }
        }
        sum += temp.toInt()
    }
    println(sum)
}
