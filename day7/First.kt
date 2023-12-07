package day7

import java.io.File

fun main() {
    val fileName = "day7/input.txt"
    val file = File(fileName)
    val list = file.readText().split("\n")
    val hands = arrayListOf<String>()
    hands.addAll(list)
    sort(hands)
    var result = 0L
    for (i in 1..hands.size) {
        result += hands[i - 1].split(" ")[1].toLong() * i
    }
    println(result)
}

fun sort(arr: ArrayList<String>) {
    val size = arr.size
    for (i in 0 until size) {
        for (j in 1 until size - i) {
            val l = arr[j - 1].split(" ")[0]
            val r = arr[j].split(" ")[0]
            if (checkValue(l) > checkValue(r)) {
                arr.swap(j, j - 1)
            }
            if (checkValue(l) == checkValue(r)) {
                if (checkValueIfDraw(l, r) == 1) {
                    arr.swap(j, j - 1)
                }
            }
        }
    }
}

fun checkValueIfDraw(card1: String, card2: String): Int {
    val figures = listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')
    for (i in card1.indices) {
        if (card1[i] != card2[i]) {
            if (figures.indexOf(card1[i]) > figures.indexOf(card2[i])) {
                return 1
            }
            if (figures.indexOf(card1[i]) < figures.indexOf(card2[i])) {
                return -1
            }
        }
    }
    return 0
}

fun checkValue(cards: String): Int {
    val map = mutableMapOf<Char, Int>()
    for (card in cards.toCharArray()) {
        map[card] = map.getOrDefault(card, 0) + 1
    }
    if (map.values.contains(5)) {
        return 10
    }
    if (map.values.contains(4)) {
        return 9
    }
    if (map.values.contains(3) && map.values.contains(2)) {
        return 8
    }
    if (map.values.contains(3)) {
        return 7
    }
    if (map.containsValue(2)) {
        var count2 = 0
        map.values.forEach { v -> if (v == 2) count2++ }
        if (count2 == 2) return 6
        return 5

    }
    return 4
}

fun <T : Any> ArrayList<T>.swap(idx: Int, idy: Int) {
    val temp = this[idx]
    this[idx] = this[idy]
    this[idy] = temp
}
