package day8

import java.io.File

fun main() {
    val fileName = "day8/input.txt"
    val file = File(fileName)
    val input = file.readText().split("\n\n")
    val instructions = input[0]
    val lines = input[1].split("\n")
    val map = mutableMapOf<String, Pair<String, String>>()
    for (line in lines) {
        val current = line.split(" = ")[0]
        val possibleMoves = line.split(" = ")[1].replace("(", "").replace(")", "").split(", ")
        map[current] = Pair(possibleMoves[0], possibleMoves[1])
    }
    var current = "AAA"
    var index = 0
    while (current != "ZZZ") {
        current = if (instructions.toCharArray()[index++ % instructions.length] == 'L')
            map[current]?.first.toString() else map[current]?.second.toString()
    }
    println(index)
}