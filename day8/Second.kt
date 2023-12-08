package day8

import java.io.File

fun main() {
    val fileName = "day8/input.txt"
    val file = File(fileName)
    val input = file.readText().split("\n\n")
    val instructions = input[0]
    val lines = input[1].split("\n")
    val map = mutableMapOf<String, Pair<String, String>>()
    val starts = mutableListOf<String>()
    for (line in lines) {
        val current = line.split(" = ")[0]
        if (current[2] == 'A') starts.add(current)
        val possibleMoves = line.split(" = ")[1].replace("(", "").replace(")", "").split(", ")
        map[current] = Pair(possibleMoves[0], possibleMoves[1])
    }
    val counters = mutableMapOf<String, Long>()
    starts.forEach { start -> counters[start] = 0 }

    for (start in starts) {
        var current = start
        var index = 0
        while (current[2] != 'Z') {
            current = if (instructions.toCharArray()[index++ % instructions.length] == 'L')
                map[current]?.first.toString() else map[current]?.second.toString()
        }
        counters[start] = index.toLong()
    }

    println(lcm(counters.values.toList()))

}

private fun lcm(a: Long, b: Long): Long {
    return a * (b / gcd(a, b))
}

private fun lcm(list: List<Long>): Long {
    var result = list[0]
    for (i in 1 until list.size) result = lcm(result, list[i])
    return result
}

private fun gcd(a: Long, b: Long): Long {
    var a = a
    var b = b
    while (b > 0) {
        val temp = b
        b = a % b
        a = temp
    }
    return a
}