package day2

import java.io.File

fun main() {
    val fileName = "day2/input.txt"
    val file = File(fileName)
    val lines = ArrayList<String>()
    file.forEachLine { lines.add(it) }
    var result = 0
    for (line in lines) {
        var leftAndRightHandler = line.split(": ")
        var samples = leftAndRightHandler[1].split("; ")
        var red = 0
        var green = 0
        var blue = 0
        for (sample in samples) {
            var colors = sample.split(", ")
            for (color in colors) {
                var size = color.split(" ")[0].toInt()
                var name = color.split(" ")[1]
                if (name == "red" && red < size) {
                    red = size
                }
                if (name == "green" && green < size) {
                    green = size
                }
                if (name == "blue" && blue < size) {
                    blue = size
                }
            }
        }
        result += red * green * blue
    }
    println(result)
}
