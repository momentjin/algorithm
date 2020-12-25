package programmers.level_4;

class 블록게임 {

    private int n;
    private int[][] board;

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0}, {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 0, 0, 0, 5, 5}, {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}};
        int n = new 블록게임().solution(board);
        System.out.println(n);
    }

    public int solution(int[][] board) {

        this.board = board;
        n = board.length;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] == 0) continue;

                if (block1(i, j)) {
                    if (canDrop(i + 1, j + 1, board[i][j]) && canDrop(i + 1, j + 2, board[i][j])) {
                        remove(i, j, i + 1, j, i + 1, j + 1, i + 1, j + 2);
                        j = -1;
                        answer++;
                    }
                } else if (block2(i, j)) {
                    if (canDrop(i + 2, j - 1, board[i][j])) {
                        remove(i, j, i + 1, j, i + 2, j, i + 2, j - 1);
                        j = -1;
                        answer++;
                    }
                } else if (block3(i, j)) {
                    if (canDrop(i + 2, j + 1, board[i][j])) {
                        remove(i, j, i + 1, j, i + 2, j, i + 2, j + 1);
                        j = -1;
                        answer++;
                    }
                } else if (block4(i, j)) {
                    if (canDrop(i + 1, j - 1, board[i][j]) && canDrop(i + 1, j - 2, board[i][j])) {
                        remove(i, j, i + 1, j, i + 1, j - 1, i + 1, j - 2);
                        j = -1;
                        answer++;
                    }
                } else if (block5(i, j)) {
                    if (canDrop(i + 1, j - 1, board[i][j]) && canDrop(i + 1, j + 1, board[i][j])) {
                        remove(i, j, i + 1, j, i + 1, j - 1, i + 1, j + 1);
                        j = -1;
                        answer++;
                    }
                }
            }
        }
        return answer;
    }

    void remove(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        board[x1][y1] = 0;
        board[x2][y2] = 0;
        board[x3][y3] = 0;
        board[x4][y4] = 0;
    }

    boolean canDrop(int limitOfRow, int y, int v) {
        for (int row = 0; row < limitOfRow; row++) {
            if (board[row][y] == 0) continue;
            if (board[row][y] != v) return false;
        }
        return true;
    }

    boolean block1(int x, int y) {
        int v = board[x][y];
        if (y + 2 >= n || x + 1 >= n) return false;
        return board[x + 1][y] == v && board[x + 1][y + 1] == v && board[x + 1][y + 2] == v;
    }

    boolean block2(int x, int y) {
        int v = board[x][y];
        if (x + 2 >= n || y - 1 < 0) return false;
        return board[x + 1][y] == v && board[x + 2][y] == v && board[x + 2][y - 1] == v;
    }

    boolean block3(int x, int y) {
        int v = board[x][y];
        if (x + 2 >= n || y + 1 >= n) return false;
        return board[x + 1][y] == v && board[x + 2][y] == v && board[x + 2][y + 1] == v;
    }

    boolean block4(int x, int y) {
        int v = board[x][y];
        if (x + 1 >= n || y - 2 < 0) return false;
        return board[x + 1][y] == v && board[x + 1][y - 1] == v && board[x + 1][y - 2] == v;
    }

    boolean block5(int x, int y) {
        int v = board[x][y];
        if (x + 1 >= n || y - 1 < 0 || y + 1 >= n) return false;
        return board[x + 1][y] == v && board[x + 1][y + 1] == v && board[x + 1][y - 1] == v;
    }
}

