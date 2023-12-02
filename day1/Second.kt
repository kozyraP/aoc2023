package day1

import java.io.File

fun replaceNums(s: String): String = s
    .replace("one", "o1e")
    .replace("two", "t2o")
    .replace("three", "t3e")
    .replace("four", "f4r")
    .replace("five", "f5e")
    .replace("six", "s6")
    .replace("seven", "s7n")
    .replace("eight", "e8t")
    .replace("nine", "n9e")

fun main() {
    val fileName = "day1/input.txt"
    val file = File(fileName)
    val lines = ArrayList<String>()
    file.forEachLine { lines.add(replaceNums(it)) }
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
