// Name: Monisha Jain
// Qualification: MCA
// College: PES University
// YOP: 2021-2023

// Given a 2D array (i.e., a matrix) containing only 1s (land) and 0s (water), find the biggest island in it. Write a function to return the area of the biggest island. 
// An island is a connected set of 1s (land) and is surrounded by either an edge or 0s (water). Each cell is considered connected to other cells horizontally or vertically (not diagonally).

import java.util.*;

public class biggestIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int biggestIslandArea = 0;

        for(int row = 0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                if(grid[row][col] == 1) {
                    biggestIslandArea = Math.max(biggestIslandArea, visitedIslandDFS(grid, row, col));
                }
            }
        }
        return biggestIslandArea;
    }

    private static int visitedIslandDFS(int[][] grid, int row, int col) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) 
            return 0;  // return if it is not a valid cell
        if(grid[row][col] == 0) 
            return 0;   // return if it is a water cell

        grid[row][col] = 0;  // mark the cell visited by making it a water cell
        int area = 1; // counting the current cell

        // recursively visit all neighbouring cells(horizontally and vertically)

        area = area + visitedIslandDFS(grid, row + 1, col); // lower cell
        area = area + visitedIslandDFS(grid, row - 1, col); // upper cell
        area = area + visitedIslandDFS(grid, row, col + 1); // right cell
        area = area + visitedIslandDFS(grid, row, col - 1); // left cell
        return area;
    }


    public static void main(String[] args) {
        biggestIsland sol = new biggestIsland();
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


// Time Complexity: 
// O(row * col), number of rows and cols of the input matrix. We have to traverse the whole matrix to find islands

// Space Complexity:
// DFS recursion stack can go row * col deep when the whole matrix is filled with 1's. Hence the SC will be O(row * col)