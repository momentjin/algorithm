package programmers.level_3

import java.util.*

/**
 * 종류 : #구현, #다시풀기(완)
 * 실패 사유
 * - 부분 수열을 구하기 위해 투포인터 전략을 썼는데, 애초에 투포인터를 쓸 필요가 없었음
 * - 스타수열의 조건중 교집합 조건으로 인해, 교집합이 될 원소 기준으로 원소쌍을 찾으면 됌
 * -
 */
class 스타수열 {

    fun solution(a: IntArray): Int {

        val numbers: MutableMap<Int, Int?> = HashMap()
        for (n in a) {
            if (numbers.containsKey(n)) {
                numbers[n] = numbers[n]!! + 1
            } else {
                numbers[n] = 1
            }
        }

        var answer = 0
        for (n in numbers.keys) {

            val cnt = numbers[n]!!
            if (cnt == 0) continue
            if (cnt <= answer) continue

            var tempCnt = 0
            var i = 0

            while (i < a.size - 1) {

                if (a[i] != n && a[i + 1] != n) {
                    i += 2
                    continue
                }

                if (a[i] == a[i + 1]) {
                    i += 2
                    continue
                }

                tempCnt++
                i += 2
            }

            answer = Integer.max(answer, tempCnt)
        }
        return answer * 2
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val n = 스타수열().solution(intArrayOf(0, 3, 3, 0, 7, 2, 0, 2, 2, 0))
            println(n)
        }
    }
}