import edu.princeton.cs.algs4.Stack;

public class Board {

    private final int dimension;
    private final int[][] numbers;

    public Board(int[][] tiles) {
        dimension = tiles.length;
        numbers = tiles.clone();
    }

    public String toString() {
        StringBuilder board = new StringBuilder();
        board.append(dimension);
        board.append('\n');
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board.append(numbers[i][j]);
                board.append(' ');
            }
            if (i != dimension - 1)
                board.append('\n');
        }
        return board.toString();
    }

    public int dimension() {
        return dimension;
    }

    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                if (numbers[i][j] != 0 && numbers[i][j] != dimension * i + j + 1)
                    hamming++;
            }
        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                if (numbers[i][j] != 0) {
                    // manhattan += Math.abs(dimension - 1 - i);
                    // manhattan += Math.abs(dimension - 1 - j);
                    manhattan += Math.abs((numbers[i][j] - 1) / dimension - i);
                    manhattan += Math
                            .abs(numbers[i][j] - 1
                                         - Math.abs((numbers[i][j] - 1) / dimension) * dimension
                                         - j);
                }
            }
        return manhattan;
    }

    public boolean isGoal() {
        if (this.hamming() == 0)
            return true;
        return false;
    }

    public boolean equals(Object y) {
        if (y == null) return false;
        if (this.getClass() != y.getClass())
            return false;

        Board other = (Board) y;
        if (dimension() != other.dimension())
            return false;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.numbers[i][j] != other.numbers[i][j])
                    return false;
            }
        }
        return true;
    }

    public Board twin() {
        int[][] aux = cloneNumbers();
        int zeroi = 0, zeroj = 0;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                if (numbers[i][j] == 0) {
                    zeroi = i;
                    zeroj = j;
                    break;
                }
            }
        if (zeroi > 0 && zeroj > 0) {
            swap(aux, zeroi - 1, zeroj, zeroi - 1, zeroj - 1);
        }
        // else if (zeroi < dimension - 1 && zeroj < dimension - 1) {
        //     swap(aux, zeroi + 1, zeroj, zeroi + 1, zeroj + 1);
        // }

        if (zeroi == 0 && zeroj > 0) {
            swap(aux, zeroi + 1, zeroj, zeroi + 1, zeroj - 1);
        }
        // else if (zeroi < dimension - 1 && zeroj == dimension - 1) {
        //     swap(aux, zeroi + 1, zeroj, zeroi + 1, zeroj - 1);
        // }

        if (zeroj == 0 && zeroi > 0) {
            swap(aux, zeroi - 1, zeroj, zeroi - 1, zeroj + 1);
        }
        // else if (zeroi == dimension - 1 && zeroj < dimension - 1) {
        //     swap(aux, zeroi - 1, zeroj, zeroi - 1, zeroj + 1);
        // }

        if (zeroi == 0 && zeroj == 0) {
            swap(aux, zeroi + 1, zeroj, zeroi + 1, zeroj + 1);
        }

        return new Board(aux);

    }

    private void swap(int[][] number, int is, int js, int id, int jd) {
        int aux = number[is][js];
        number[is][js] = number[id][jd];
        number[id][jd] = aux;
    }

    private int[][] cloneNumbers() {
        int[][] aux = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                aux[i][j] = numbers[i][j];
        return aux;
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();
        int zeroi = 0, zeroj = 0;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                if (numbers[i][j] == 0) {
                    zeroi = i;
                    zeroj = j;
                    break;
                }
            }

        if (zeroi > 0) {

            int[][] aux = cloneNumbers();
            swap(aux, zeroi, zeroj, zeroi - 1, zeroj);
            neighbors.push(new Board(aux));
        }

        if (zeroi < dimension - 1) {
            int[][] aux = cloneNumbers();
            swap(aux, zeroi, zeroj, zeroi + 1, zeroj);
            neighbors.push(new Board(aux));
        }

        if (zeroj > 0) {
            int[][] aux = cloneNumbers();
            swap(aux, zeroi, zeroj, zeroi, zeroj - 1);
            neighbors.push(new Board(aux));
        }

        if (zeroj < dimension - 1) {
            int[][] aux = cloneNumbers();
            swap(aux, zeroi, zeroj, zeroi, zeroj + 1);
            neighbors.push(new Board(aux));
        }
        return neighbors;
    }

    public static void main(String[] args) {
    }
}
