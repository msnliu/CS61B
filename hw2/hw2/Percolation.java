package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int len;
    private int top;
    private int bottom;
    private int numOfOpen = 0;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufExcludeBottom;
    private int[][] surroundings = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int xyTo1D(int row, int col) {
        return row * len + col + 1;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        // create N-by-N grid, with all sites initially blocked
        len = N;
        top = 0;
        bottom = N * N + 1;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufExcludeBottom = new WeightedQuickUnionUF(N * N + 1);
    }
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOfOpen += 1;
        }
        // open the site (row, col) if it is not open already
        if (row == 0) {
            uf.union(xyTo1D(row, col), top);
            ufExcludeBottom.union(xyTo1D(row, col), top);
        }
        if (row == len - 1) {
            uf.union(xyTo1D(row, col), bottom);
        }
        for (int[] surrounding : surroundings) {
            int newrow = row + surrounding[0];
            int newcol = col + surrounding[1];
            if (newrow >= 0 && newrow < len) {
                if (newcol >= 0 && newcol < len) {
                    if (isOpen(newrow, newcol)) {
                        uf.union(xyTo1D(row, col), xyTo1D(newrow, newcol));
                        ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(newrow, newcol));
                    }
                }
            }
        }
    }
    public boolean isOpen(int row, int col) {
        validate(row, col);
        // is the site (row, col) open?
        return grid[row][col];
    }
    public boolean isFull(int row, int col) {
        validate(row, col);
        // is the site (row, col) full?
        return ufExcludeBottom.connected(xyTo1D(row, col), top);
    }
    public int numberOfOpenSites() {
        // number of open sites
        return numOfOpen;
    }
    public boolean percolates() {
        // does the system percolate?
        return uf.connected(top, bottom);
    }

    public void validate(int row, int col) {
        if (row < 0 || row > len - 1 || col < 0 || col > len - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {
        // use for unit testing (not required, but keep this here for the autograder)
    }
}
