package programmers.level_2

import java.util.*

/**
 * 종류 : #단순, #큐
 * 성공/실패 : 성공
 * 소요 시간 : 약 20분
 */
fun solution(priorities: IntArray, location: Int): Int {

    val q = LinkedList<Number>()
    for ((idx, priority) in priorities.withIndex()) {
        println("${idx}, ${priority}")
        q.add(Number(idx,priority))
    }

    var cnt = 0
    println("큐 : ${q}")

    while (!q.isEmpty()) {

        val head = q.poll()
        val headVal = head.value
        val maxVal = q.maxBy(Number::value)?.value ?: return ++cnt

        if (headVal >= maxVal) {
            println("프린트 : ${head}")
            println("큐 : ${q}")
            cnt++

            if (head.idx == location)
                return cnt

            continue
        }

        else {
            q.add(head)
            println("최대값이 아니라서 끝으로 이동")
            println("큐 : ${q}")
        }
    }


    return 0
}

data class Number (val idx:Int, val value:Int) {

    override fun toString(): String {
        return value.toString()
    }
}

fun main( ) {
    val priorities = listOf(7,7,7,7,7,7)
    val location= 5
    val result = solution(priorities.toIntArray(), location)
    print(result)
}
