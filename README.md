# Biggest-Island
Given a 2D array (i.e., a matrix) containing only 1s (land) and 0s (water), find the biggest island in it. Write a function to return the area of the biggest island. 
The question follows the Island pattern and is quite similar to the Number of Islands problem.

We will traverse the matrix linearly to find islands.

Whenever we find a cell with the value '1' (i.e., land), we have found an island. Using that cell as the root node, we will perform a Depth First Search (DFS) or Breadth First Search (BFS) to find all of its connected land cells. During our DFS or BFS traversal, we will find and mark all the horizontally and vertically connected land cells. 

We will keep a variable to remember the maximum area of any island.

Algorithm Walkthrough

Here is the detailed walkthrough of the DFS algorithm:

We first initialize biggestIslandArea to 0. This variable will keep track of the largest island area we have encountered.
Then, we traverse each cell in the matrix. If the cell's value is 1 (land), we begin a DFS search from this cell using the visitIslandDFS function. This function will visit the cell and all its neighboring cells that are part of the same island.
In the visitIslandDFS function, we first check if the current cell (x, y) is within the boundaries of the matrix and if it's a land cell. If it's not, we return 0.
We then mark the current cell as visited by setting its value to 0 (water). This helps avoid visiting the same cell again and ending up in an infinite loop.
We initialize the area of the current island to 1 (counting the current cell), and then add to it the areas returned by the recursive DFS calls for the neighboring cells (top, bottom, left, and right).
After we finish the DFS for a cell (meaning we have visited all cells in the island that the cell is a part of), we update biggestIslandArea with the maximum of its current value and the area of the island we just finished exploring.
Finally, after traversing all cells in the matrix, we return the biggestIslandArea, which now holds the area of the largest island.


import java.util.*;

class Solution {
  public int maxAreaOfIsland(int[][] matrix) {

    int rows = matrix.length;
    int cols = matrix[0].length;
    int biggestIslandArea = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == 1) { // only if the cell is a land
          // we have found an island
          biggestIslandArea = Math.max(biggestIslandArea, visitIslandDFS(matrix, i, j));
        }
      }
    }
    return biggestIslandArea;
  }

  private static int visitIslandDFS(int[][] matrix, int x, int y) {
    if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length)
      return 0; // return, if it is not a valid cell
    if (matrix[x][y] == 0)
      return 0; // return, if it is a water cell

    matrix[x][y] = 0; // mark the cell visited by making it a water cell

    int area = 1; // counting the current cell
    // recursively visit all neighboring cells (horizontally & vertically)
    area += visitIslandDFS(matrix, x + 1, y); // lower cell
    area += visitIslandDFS(matrix, x - 1, y); // upper cell
    area += visitIslandDFS(matrix, x, y + 1); // right cell
    area += visitIslandDFS(matrix, x, y - 1); // left cell

    return area;
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    System.out.println(sol.maxAreaOfIsland(
        new int[][] {
            { 1, 1, 1, 0, 0 },
            { 0, 1, 0, 0, 1 },
            { 0, 0, 1, 1, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 1, 0, 0 }
        }));
  }
}

Time Complexity
Time complexity of the above algorithm will be , where ‘M’ is the number of rows and 'N' is the number of columns of the input matrix. This is due to the fact that we have to traverse the whole matrix to find islands.

Space Complexity
DFS recursion stack can go  deep when the whole matrix is filled with '1's. Hence, the space complexity will be , where ‘M’ is the number of rows and 'N' is the number of columns of the input matrix.


