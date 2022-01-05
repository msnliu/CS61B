import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class BubbleGrid {
    private int[][] grid;
    private int rowNum;
    private int colNum;
    private int topmost = 0;
    private int[][] surroundings = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int[] result;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        rowNum = grid.length;
        colNum = grid[0].length;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // get to the final state where all darts have been shot
        for (int[] dart : darts) {
            if (grid[dart[0]][dart[1]] == 1) {
                grid[dart[0]][dart[1]] = 2;
            }
        }

        // connect the final remaining grids after all darts have been shot
        UnionFind uf = new UnionFind(rowNum * colNum + 1);
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == 1) {
                    unionSurrounding(i, j, uf);
                }
            }
        }

        int numOfStuck = uf.sizeOf(topmost);

        result = new int[darts.length];
        for (int j = darts.length - 1; j >= 0; j--) {
            int[] t = darts[j];
            // reversely add dart
            if (grid[t[0]][t[1]] == 2) {
                grid[t[0]][t[1]] = 1;
            }
            unionSurrounding(t[0], t[1], uf);
            int temp = uf.sizeOf(topmost);
            // minus pop itself!
            result[j] = temp - numOfStuck - 1;
            numOfStuck = temp;
        }
        return result;
    }

    public void unionSurrounding(int row, int col, UnionFind uf) {
        if (row == 0) {
            uf.connect(topmost, xyto1D(row, col));
        }
        for (int[] surrounding : surroundings) {
            int newrow = row + surrounding[0];
            int newcol = col + surrounding[1];
            if (validate(newrow, rowNum) && validate(newcol, colNum)) {
                if (grid[newrow][newcol] == 1) {
                    uf.connect(xyto1D(row, col), xyto1D(newrow, newcol));
                }
            }
        }
    }

    public boolean validate(int dim, int ref) {
        return dim >= 0 && dim < ref;
    }

    public int xyto1D(int row, int col) {
        return row * colNum + col + 1;
    }
}
