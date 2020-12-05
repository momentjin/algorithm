package programmers.level_2

import java.util.*

/**
 * 종류 : #단순, #큐, #다시풀기
 * 성공/실패 : 실패
 * 실패 사유
 * - 풀면 풀 순 있었을 텐데 굉장히 오래 걸렸을 것이고, 복잡하게 풀었을 것 같음
 * - 시간의 순서에 따라 당연히 처리해야 하는 줄 알아서 자꾸 복잡하게 생각했던 것 같음
 * - 이 문제는 시간? 보다는 언제 queue에서 빼야하고, 넣어야하는지 그리고 언제 시간을 증가시켜야하는지를 명확히 알면 시간적으로 생각하지 않고 쉽게 풀 수 있는 것 같음
 */
fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {

    val bridge: Queue<Int> = LinkedList()
    var currentBridgeWeight = 0
    var seconds = 0

    for (truckWeight in truck_weights) {

        while (true) {

            // 다리 위에 아무 트럭도 없는 경우
            if (bridge.isEmpty()) {
                bridge.add(truckWeight);
                currentBridgeWeight += truckWeight
                seconds++
                break
            }

            // 다리 길이와 현재 큐의 길이 즉, 다리위의 트럭이 갯수 같은 경우
            else if (bridge_length == bridge.size) {
                currentBridgeWeight -= bridge.poll()
            } else {

                // 트럭이 진입할 수 있는 경우
                if (currentBridgeWeight + truckWeight <= weight) {
                    bridge.add(truckWeight)
                    currentBridgeWeight += truckWeight
                    seconds++
                    break
                }

                // 다리가 견딜 수 있는 무게를 초과하기 때문에, 트럭이 진입할 수 없는 경우
                else {

                    bridge.add(0) // 무게 0짜리 트럭을 넣고, 트럭 갯수가 꽉차있는 것 처럼 보이게 한뒤 루프를 다시 돌아 통과시키기 위함
                    seconds++
                }
            }
        }
    }

    return seconds + bridge_length
}

//
//fun main() {
//    val bridge_length = 2
//    val weight = 10
//    val truck_weights = listOf(7, 4, 5, 6).toIntArray()
//    val result = 외벽점검().solution(bridge_length, weight, truck_weights)
//
//    println(result)
//}
