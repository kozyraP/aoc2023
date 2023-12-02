package day2

import java.io.File

fun main() {
    val fileName = "day2/input.txt"
    val file = File(fileName)
    val lines = ArrayList<String>()
    file.forEachLine { lines.add(it) }

    var maxCubes = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    var result = 0

    for (line in lines) {
        var leftAndRightHandler = line.split(": ")
        var id = leftAndRightHandler[0].split(" ")[1]
        var samples = leftAndRightHandler[1].split("; ")
        var flag = true
        for (sample in samples) {
            var colors = sample.split(", ")
            for (color in colors) {
                var size = color.split(" ")[0].toInt()
                var name = color.split(" ")[1]
                if (size > maxCubes[name]!!) {
                    flag = false
                }
            }
        }
        if (flag) result += id.toInt()
    }
    println(result)
}
