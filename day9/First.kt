package day9


import java.io.File

fun main() {
    val fileName = "day9/input.txt"
    val file = File(fileName)
    val input = file.readText().split("\n")
    val lines = mutableListOf<List<Int>>()
    for (line in input) {
        lines.add(line.split(" ").map { it.toInt() })
    }
    var resultFirst = 0
    var resultSecond = 0
    for (line in lines) {
        resultFirst += getListOfDifferences(line)
        resultSecond += getListOfDifferences(line.reversed())
    }
    println(resultFirst)
    println(resultSecond)
}

private fun getListOfDifferences(line: List<Int>): Int {
    var exit = true
    for (num in line) {
        if (num != 0) {
            exit = false
            break
        }
    }
    if (exit) return 0

    var newLine = mutableListOf<Int>()
    for (i in 1..<line.size) {
        val difference = line[i] - line[i - 1]
        newLine.add(difference)
    }
    return getListOfDifferences(newLine) + line[line.size - 1]
}