package matrix;

/**
 * Problem Link: <a href="https://leetcode.com/problems/search-a-2d-matrix-ii/">Search a 2D Matrix II</a>
 */
public class SearchMatrix {

    // Brute Force
    public boolean searchMatrix1(int[][] matrix, int target) {
        for (int[] ints : matrix) for (int anInt : ints) if (anInt == target) return true;
        return false;
    }

    // Enhanced Brute Force (using binary search for longest row or col
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length < matrix[0].length) return searchBinCol(matrix, target);
        else return searchBinRow(matrix, target);
    }

    private boolean searchBinRow(int[][] matrix, int target) {
        for (int c = 0; c < matrix[0].length; c++) {
            int top = 0, bottom = matrix.length - 1, middle = bottom / 2;
            do {
                if (matrix[middle][c] == target) return true;
                if (matrix[middle][c] < target) top = middle + 1;
                else bottom = middle - 1;
                middle = (top + bottom) / 2;
            } while (top <= bottom);
        }
        return false;
    }

    private boolean searchBinCol(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int left = 0, right = matrix[0].length - 1, middle = right / 2;
            do {
                if (row[middle] == target) return true;
                if (row[middle] < target) left = middle + 1;
                else right = middle - 1;
                middle = (left + right) / 2;
            } while (left <= right);
        }
        return false;
    }


    // Optimal binary search like
    public boolean searchMatrix3(int[][] matrix, int target) {
        int r = 0, c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) return true;
            if (matrix[r][c] < target) r++;
            else c--;
        }
        return false;
    }

    public static void test() {
        SearchMatrix s = new SearchMatrix();
        assert s.searchMatrix(new int[][]{
                        {1, 4, 7, 11, 15},
                        {2, 5, 8, 12, 19},
                        {3, 6, 9, 16, 22},
                        {10, 13, 14, 17, 24},
                        {18, 21, 23, 26, 30}},
                5);
        assert !s.searchMatrix(new int[][]{
                        {1, 4, 7, 11, 15},
                        {2, 5, 8, 12, 19},
                        {3, 6, 9, 16, 22},
                        {10, 13, 14, 17, 24},
                        {18, 21, 23, 26, 30}},
                20);
        assert !s.searchMatrix(new int[][]{{1, 1}}, 0);
        assert s.searchMatrix(new int[][]{{-1, 3}}, 3);
        assert !s.searchMatrix(new int[][]{{1, 3, 5}}, -1);
        assert s.searchMatrix(new int[][]{{5}, {6}}, 6);
        assert s.searchMatrix(new int[][]{{1, 3, 5}}, 1);
        assert s.searchMatrix(new int[][]{{1, 4}, {2, 5}}, 2);
        assert s.searchMatrix(new int[][]{
                        {1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10},
                        {11, 12, 13, 14, 15},
                        {16, 17, 18, 19, 20},
                        {21, 22, 23, 24, 25}},
                15);

        assert s.searchMatrix(new int[][]{
                        {1, 3, 5, 7, 9},
                        {2, 4, 6, 8, 10},
                        {11, 13, 15, 17, 19},
                        {12, 14, 16, 18, 20},
                        {21, 22, 23, 24, 25}},
                13);
    }
}
