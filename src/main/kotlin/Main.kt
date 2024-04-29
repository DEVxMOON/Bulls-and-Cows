package hr.bullsAndCows

fun main() {

    val size = 3
    val answer = makeAnswer(size)

    while (true) {
        println("숫자를 입력하세요")
        val numberSheet = readln().map { it.toString().toInt() }.toIntArray()

        if (numberSheet.size != size) {
            println("범위 외 입력입니다.")
            continue
        } else if (hasDuplicates(numberSheet)) {
            println("중복된 숫자가 있습니다.")
            continue
        } else {
            val result = checkGuess(answer, numberSheet)
            println(result)
            if (result == "3S") {
                println("정답!")
                break
            }
        }
    }
}

fun makeAnswer(size: Int): IntArray = (1..9).toList().shuffled().take(size).toIntArray()

fun hasDuplicates(arr: IntArray): Boolean {
    return arr.size != arr.distinct().size
}

fun checkGuess(answer: IntArray, guess: IntArray): String {
    var strikes = 0
    var balls = 0

    for (i in answer.indices) {
        if (answer[i] == guess[i]) {
            strikes++
        } else if (answer.contains(guess[i])) {
            balls++
        }
    }

    return when {
        strikes == 3 -> "3S"
        strikes > 0 && balls > 0 -> "${strikes}S ${balls}B"
        strikes > 0 -> "${strikes}S"
        balls > 0 -> "${balls}B"
        else -> "OUT"
    }
}