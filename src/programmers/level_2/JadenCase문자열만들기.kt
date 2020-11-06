package programmers.level_2

/**
 * 종류 : #구현
 * 성공/실패 : 성공
 */
fun main() {

    val input = "3a         aaaaa bb 333"
    val result = solution(input)
    println(result)
}

fun solution(s: String): String {


    val result = s
        .split(" ")
        .map { word ->
            if (word.isNotBlank() && word[0].isLetter()) {
                val sb = StringBuilder(word.toLowerCase())
                sb[0] = word[0].toUpperCase()
                sb.toString()
            }
            else
                word
        }
        .toList()
        .joinToString(" ")


    return result
}
