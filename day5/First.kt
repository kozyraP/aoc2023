package day5

import java.io.File

fun main() {
    val fileName = "day5/input.txt"
    val file = File(fileName)
    val parts = file.readText().split("\n\n")
    val seeds = parts[0].split(": ")[1].split(" ").map { it.toLong() }.toMutableList()
    val listOfMaps = ArrayList<List<List<Long>>>()

    for (part in parts.subList(1, parts.size)) {
        val list = ArrayList<List<Long>>()
        val partDropText = part.split("\n")
        for (coordinates in partDropText.subList(1, partDropText.size)) {
            list.add(coordinates.split(" ").map { it.toLong() })
        }
        listOfMaps.add(list)
    }
    for (i in 0..<seeds.size) {
        for (maps in listOfMaps) {
            for (map in maps) {
                val destination = map[0]
                val source = map[1]
                val range = map[2]
                if (source <= seeds[i] && seeds[i] < source + range) {
                    seeds[i] = seeds[i] - source + destination
                    break
                }
            }
        }
    }
    println(seeds.min())
}
