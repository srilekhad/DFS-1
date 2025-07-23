//Time Complexity: O(m × n) — Each cell is visited at most once, where m is rows and n is columns.
//Space Complexity: O(m × n) — In the worst case, the recursion stack may go as deep as the total number of pixels.

//Use DFS to traverse in all 4 directions from the starting pixel.
//Replace only the pixels that match the original color.
//Recursively update neighbors until the entire connected region is filled.

class Solution {
    int[][] directions;
    int rowCount, colCount;

    public int[][] floodFill(int[][] image, int startRow, int startCol, int newColor) {
        this.directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        this.rowCount = image.length;
        this.colCount = image[0].length;

        int originalColor = image[startRow][startCol];
        if (originalColor == newColor) return image;

        applyFloodFill(image, startRow, startCol, newColor, originalColor);

        return image;
    }

    private void applyFloodFill(int[][] image, int row, int col, int newColor, int originalColor) {
        if (row < 0 || col < 0 || row == rowCount || col == colCount || image[row][col] != originalColor) return;

        image[row][col] = newColor;

        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            applyFloodFill(image, nextRow, nextCol, newColor, originalColor);
        }
    }
}
