/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] a;

    private WeightedQuickUnionUF wqu;

    private int counter = 0;


    public Percolation(int n) {

        if (n <= 0)
            throw new IllegalArgumentException("n can't be <= 0");
        else {
            a = new int[n + 1][n + 1];

            wqu = new WeightedQuickUnionUF(n * n + 2);
        }
    }


    public void open(int row, int col) {
        if (row <= 0 || row > (a.length - 1) || col <= 0 || col > (a.length - 1))
            throw new IllegalArgumentException("argument out of bounds");
        else {
            if (a[row][col] == 0) {
                counter++;
                a[row][col] = 1;
                if (row == 1) {
                    wqu.union((row - 1) * (a.length - 1) + col - 1,
                              (a.length - 1) * (a.length - 1) + 1);
                    if (a[row + 1][col] == 1)
                        wqu.union((row - 1) * (a.length - 1) + col - 1,
                                  row * (a.length - 1) + col - 1);
                    if (col != 1)
                        if (a[row][col - 1] == 1)
                            wqu.union((row - 1) * (a.length - 1) + col - 1,
                                      (row - 1) * (a.length - 1) + col - 2);
                    if (col != (a.length - 1))
                        if (a[row - 1][col + 1] == 1)
                            wqu.union((row - 1) * (a.length - 1) + col - 1,
                                      (row - 1) * (a.length - 1) + col);
                }
                else if (row == (a.length - 1)) {
                    wqu.union((row - 1) * (a.length - 1) + col - 1,
                              (a.length - 1) * (a.length - 1));
                    if (a[row - 1][col] == 1)
                        wqu.union((row - 1) * (a.length - 1) + col - 1,
                                  (row - 2) * (a.length - 1) + col - 1);
                    if (col != 1)
                        if (a[row][col - 1] == 1)
                            wqu.union((row - 1) * (a.length - 1) + col - 1,
                                      (row - 1) * (a.length - 1) + col - 2);
                    if (col != (a.length - 1))
                        if (a[row - 1][col + 1] == 1)
                            wqu.union((row - 1) * (a.length - 1) + col - 1,
                                      (row - 1) * (a.length - 1) + col);
                }
                else {
                    if (a[row - 1][col] == 1)
                        wqu.union((row - 1) * (a.length - 1) + col - 1,
                                  (row - 2) * (a.length - 1) + col - 1);
                    if (a[row + 1][col] == 1)
                        wqu.union((row - 1) * (a.length - 1) + col - 1,
                                  row * (a.length - 1) + col - 1);
                    if (col != 1)
                        if (a[row][col - 1] == 1)
                            wqu.union((row - 1) * (a.length - 1) + col - 1,
                                      (row - 1) * (a.length - 1) + col - 2);
                    if (col != (a.length - 1))
                        if (a[row][col + 1] == 1)
                            wqu.union((row - 1) * (a.length - 1) + col - 1,
                                      (row - 1) * (a.length - 1) + col);

                }

            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > (a.length - 1) || col <= 0 || col > (a.length - 1)) {
            throw new IllegalArgumentException("argument out of bounds");
        }
        else {
            if (a[row][col] == 1)
                return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row > (a.length - 1) || col <= 0 || col > (a.length - 1)) {
            throw new IllegalArgumentException("argument out of bounds");
        }
        else {
            if (a[row][col] == 1 && (wqu
                    .connected((row - 1) * (a.length - 1) + col - 1,
                               (a.length - 1) * (a.length - 1) + 1)))
                return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return counter;
    }

    public boolean percolates() {
        if (wqu.connected((a.length - 1) * (a.length - 1), (a.length - 1) * (a.length - 1) + 1))
            return true;
        return false;
    }

    public static void main(String[] args) {
    }
}
