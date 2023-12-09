package arrays.sortingAndSearching;

import java.util.HashSet;

public class IsValidSudoku {
    private char[][] board;

    // Brute Force, Fastest solution
    public boolean isValidSudoku1(char[][] board) {
        this.board = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.' && (invalidCol(i, j) || invalidRow(i, j))) return false;
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (invalidSubGrid(row * 3, col * 3)) return false;
            }
        }
        return true;
    }

    private boolean invalidRow(int i, int j) {
        for (int k = 0; k < board.length; k++)
            if (i != k && board[i][j] == board[k][j]) return true;
        return false;
    }

    private boolean invalidCol(int i, int j) {
        for (int k = 0; k < board[0].length; k++)
            if (j != k && board[i][j] == board[i][k])  return true;
        return false;
    }

    private boolean invalidSubGrid(int row, int col) {
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (board[i][j] != '.' && isRepeated(i, j, row, col)) return true;
            }
        }
        return false;
    }

    private boolean isRepeated(int i1, int j1, int row, int col) {
        for (int i = row; i < row + 3; i++) {
            for (int j = col + 1; j < col + 3; j++) {
                if (i != i1 && j != j1 && board[i1][j1] == board[i][j]) return true;
            }
        }
        return false;
    }

    // Least optimal but most maintainable
    public boolean isValidSudoku(char[][] board) {
        // Create hash sets for rows, columns, and sub-grids
        HashSet<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char currentVal = board[i][j];
                // Check for duplicates in rows, columns, and sub-grids
                if (currentVal != '.'
                        && (!seen.add(currentVal + " in row " + i)
                        || !seen.add(currentVal + " in column " + j)
                        || !seen.add(currentVal + " in subgrid " + i / 3 + "-" + j / 3))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void test() {
        IsValidSudoku s = new IsValidSudoku();
        assert s.isValidSudoku(new char[][]
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}
        );
        assert !s.isValidSudoku(new char[][]
                {{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}
        );

        assert !s.isValidSudoku(new char[][]
                {{'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                        {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                        {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                        {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                        {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                        {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                        {'.', '.', '4', '.', '.', '.', '.', '.', '.'}}
        );

        assert s.isValidSudoku(new char[][]
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}
        );
    }
}
