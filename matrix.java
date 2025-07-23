//Time Complexity: O(m × n) — each cell is visited twice (once in each DP pass).
//Space Complexity: O(1) (excluding output) — updates are made in-place in the result matrix.

//Initialize a result matrix with 99999 for all 1s and 0 for 0s.
//Do a top-left to bottom-right pass, updating each cell using top and left neighbors.
//Do a bottom-right to top-left pass, updating each cell using bottom and right neighbors.

class Solution {
    int rowCount, colCount;
    int[][] distanceMatrix;

    public int[][] updateMatrix(int[][] inputMatrix) {
        this.rowCount = inputMatrix.length;
        this.colCount = inputMatrix[0].length;

        this.distanceMatrix = new int[rowCount][colCount];

        // Step 1: Initialize cells with 99999 for 1s
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (inputMatrix[row][col] == 1) {
                    distanceMatrix[row][col] = 99999;
                }
            }
        }

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (inputMatrix[row][col] == 1) {
                    if (row > 0 && col > 0) {
                        distanceMatrix[row][col] = Math.min(distanceMatrix[row - 1][col], distanceMatrix[row][col - 1]) + 1;
                    } else if (row > 0) {
                        distanceMatrix[row][col] = distanceMatrix[row - 1][col] + 1;
                    } else if (col > 0) {
                        distanceMatrix[row][col] = distanceMatrix[row][col - 1] + 1;
                    }
                }
            }
        }

        for (int row = rowCount - 1; row >= 0; row--) {
            for (int col = colCount - 1; col >= 0; col--) {
                if (inputMatrix[row][col] == 1) {
                    if (row < rowCount - 1 && col < colCount - 1) {
                        distanceMatrix[row][col] = Math.min(distanceMatrix[row][col], Math.min(distanceMatrix[row + 1][col], distanceMatrix[row][col + 1]) + 1);
                    } else if (row < rowCount - 1) {
                        distanceMatrix[row][col] = Math.min(distanceMatrix[row][col], distanceMatrix[row + 1][col] + 1);
                    } else if (col < colCount - 1) {
                        distanceMatrix[row][col] = Math.min(distanceMatrix[row][col], distanceMatrix[row][col + 1] + 1);
                    }
                }
            }
        }

        return distanceMatrix;
    }
}
