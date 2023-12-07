package day7

import java.io.File

fun main() {
    val fileName = "day7/input.txt"
    val file = File(fileName)
    val list = file.readText().split("\n")
    val hands = arrayListOf<String>()
    hands.addAll(list)
    sort2(hands)
    var result = 0L
    for (i in 1..hands.size) {
        result += hands[i - 1].split(" ")[1].toLong() * i
    }
    println(result)
}

fun sort2(arr: ArrayList<String>) {
    val size = arr.size
    for (i in 0 until size) {
        for (j in 1 until size - i) {
            val l = arr[j - 1].split(" ")[0]
            val r = arr[j].split(" ")[0]
            if (checkValue2(l) > checkValue2(r)) {
                arr.swap(j, j - 1)
            }
            if (checkValue2(l) == checkValue2(r)) {
                if (checkValueIfDraw2(l, r) == 1) {
                    arr.swap(j, j - 1)
                }
            }
        }
    }
}

fun checkValueIfDraw2(cardsLeft: String, cardsRight: String): Int {
    val cardValue = listOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A')
    for (i in cardsLeft.indices) {
        if (cardsLeft[i] != cardsRight[i]) {
            if (cardValue.indexOf(cardsLeft[i]) > cardValue.indexOf(cardsRight[i])) {
                return 1
            }
            if (cardValue.indexOf(cardsLeft[i]) < cardValue.indexOf(cardsRight[i])) {
                return -1
            }
        }
    }
    return 0
}

fun checkValue2(cards: String): Int {
    val map = mutableMapOf(Pair(' ', 0)) //add something to map to avoid error
    var jokers = 0
    for (card in cards.toCharArray()) {
        if (card == 'J') {
            jokers++
            continue
        }
        map[card] = map.getOrDefault(card, 0) + 1
    }
    if (map.values.max() + jokers == 5) {
        return 10
    }
    if (map.values.max() + jokers == 4) {
        return 9
    }
    if (map.values.max() + jokers == 3) {
        map.removeLargestValue(map.values.max())
        if (map.values.max() == 2) {
            return 8
        }
        return 7
    }
    if (map.values.max() + jokers == 2) {
        map.removeLargestValue(map.values.max())
        if (map.values.max() == 2) {
            return 6
        }
        return 5
    }
    return 4
}

fun MutableMap<Char, Int>.removeLargestValue(maxValue: Int) {
    var keyToDelete = ' '
    for ((k, v) in this) {
        if (v == maxValue) {
            keyToDelete = k
        }
    }
    this.remove(keyToDelete)
}