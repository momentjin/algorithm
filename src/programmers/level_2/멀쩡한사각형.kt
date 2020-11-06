package programmers.level_2

import java.math.BigInteger


/**
 * 종류 : #구현 #규칙
 * 성공/실패 : 실패
 * 실패 사유
 * - 규칙을 찾지 못함
 */
fun solution(w: Int, h: Int): Long {


    val _w: BigInteger = BigInteger.valueOf(w.toLong())
    val _h: BigInteger = BigInteger.valueOf(h.toLong())
    val gcd = _w.gcd(_h).toLong()

    return (w.toLong() * h.toLong()) - (w.toLong() + h.toLong() - gcd)
}

