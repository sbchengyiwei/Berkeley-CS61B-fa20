package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf1;
    private WeightedQuickUnionUF uf2; // To avoid backwash
    private int numOpenSites = 0;
    private int[][] surroundings = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * Initialize the grid
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        size = N;
        top = 0;
        bottom = N * N + 1;
        uf1 = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);
    }

    /**
        Transform (row, col) to 1D coordinate
     */
    private int xyTo1D(int row, int col) {
        return row * size + col + 1;
    }

    /**
     Validate the validity of (row, col)
     */

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Open the site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites += 1;
        }
        if (row == 0) {
            uf1.union(xyTo1D(row, col), top);
            uf2.union(xyTo1D(row, col), top);
        }
        if (row == size - 1) {
            uf1.union(xyTo1D(row, col), bottom);
        }
        for (int[] surrounding : surroundings) {
            int adjacentRow = row + surrounding[0];
            int adjacentCol = col + surrounding[1];
            if (adjacentRow >= 0 && adjacentRow < size) {
                if (adjacentCol >= 0 && adjacentCol < size) {
                    if (isOpen(adjacentRow, adjacentCol)) {
                        uf1.union(xyTo1D(row, col), xyTo1D(adjacentRow, adjacentCol));
                        uf2.union(xyTo1D(row, col), xyTo1D(adjacentRow, adjacentCol));
                    }
                }
            }
        }
    }


    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf2.connected(xyTo1D(row, col), top);
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        return uf1.connected(top, bottom);
    }

    public static void main(String[] args) {

    }

}
