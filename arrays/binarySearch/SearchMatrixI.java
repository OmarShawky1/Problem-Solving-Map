package arrays.binarySearch;

public class SearchMatrixI {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1, mid;

        while (row < matrix.length && col >= 0) {
            mid = matrix[row][col];
            if (mid == target) return true;
            else if (mid > target) col--;
            else row++;
        }
        return false;
    }

    public static void test() {
        SearchMatrixI s = new SearchMatrixI();
        assert s.searchMatrix(new int[][]{
                          {1, 3, 5, 7}
                        , {10, 11, 16, 20}
                        , {23, 30, 34, 60}}
                , 3);
    }
}
