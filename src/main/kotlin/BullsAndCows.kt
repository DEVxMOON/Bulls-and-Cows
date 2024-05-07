package hr.bullsAndCows

class BullsAndCows(private val size: Int) {
    private val scoreList = mutableListOf<Int>()

    init {
        println("환영합니다! 원하시는 번호를 입력해주세요")
    }

    fun start() {
        while (true) {
            println("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기")
            // 숫자가 아닌 값이 입력될 경우 핸들링
            val input = readln()
            var num: Int
            if (input.matches(Regex("[0-9]+"))) {
                num = input.toInt()

                when (num) {
                    1 -> {
                        println("< 게임을 시작합니다 >")
                        val answer = makeAnswer(size)
                        scoreList.add(runGame(answer))
                    }

                    2 -> {
                        printScore(scoreList)
                    }

                    3 -> {
                        println("< 숫자 야구 게임을 종료합니다 >")
                        break
                    }

                    else -> {
                        println("< 올바른 숫자를 입력해주세요! >")
                    }
                }
            } else {
                println("< 올바른 숫자를 입력해주세요! >")
            }
        }
    }


    private fun makeAnswer(size: Int): IntArray {
        while (true) {
            val answer = (1..9).toList().shuffled().take(size).toIntArray()
            if (answer[0] != 0) return answer
        }
    }

    private fun hasDuplicates(arr: IntArray): Boolean {
        return arr.size != arr.distinct().size
    }

    private fun checkGuess(answer: IntArray, guess: IntArray): String {
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

    private fun runGame(answer: IntArray): Int {
        var cnt = 1
        while (true) {
            println("숫자를 입력하세요")
            val numberSheet = readln().map { it.toString().toInt() }.toIntArray()
            if (checkNumber(numberSheet)) {
                val result = checkGuess(answer, numberSheet)
                println(result)
                if (result == "3S") {
                    println("정답!")
                    return cnt
                }
                cnt++
            }
        }
    }

    //숫자 검증 분리
    private fun checkNumber(numberSheet: IntArray): Boolean {
        if (numberSheet.size != size) {
            println("범위 외 입력입니다.")
            return false
        } else if (hasDuplicates(numberSheet)) {
            println("중복된 숫자가 있습니다.")
            return false
        } else if (numberSheet[0] == 0) {
            println("숫자는 0으로 시작할 수 없습니다.")
            return false
        }
        return true
    }

    private fun printScore(scoreList: MutableList<Int>) {
        println("< 게임 기록 보기 >")
        scoreList.forEachIndexed { index, score ->
            println("${index + 1}번째 게임 : 시도 횟수 - $score")
        }
    }

}