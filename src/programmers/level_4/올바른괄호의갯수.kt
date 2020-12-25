package programmers.level_4

/**
 * #DFS
 * 소요시간: 16분
 */
class 올바른괄호의갯수 {

    fun solution(n: Int): Int {
        answer = 0
        dfs(n, n, 0)
        return answer
    }

    fun dfs(n: Int, open: Int, close: Int) {
        if (open == 0 && close == n) {
            answer++
            return
        }
        if (open > 0) dfs(n, open - 1, close)
        if (n - open > close) dfs(n, open, close + 1)
    }

    companion object {
        var answer = 0

        @JvmStatic
        fun main(args: Array<String>) {
            val n = 올바른괄호의갯수().solution(2)
            println(n)
        }
    }
}