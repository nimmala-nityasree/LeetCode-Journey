class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char num = board[row][col];
                if (num == '.') continue;

                for (int i = 0; i < 9; i++) {
                    if (i != col && board[row][i] == num) return false;
                    if (i != row && board[i][col] == num) return false;

                    int boxRow = 3 * (row / 3) + (i / 3);
                    int boxCol = 3 * (col / 3) + (i % 3);

                    if ((boxRow != row || boxCol != col) &&
                        board[boxRow][boxCol] == num) return false;
                }
            }
        }
        return true;
    }
}

